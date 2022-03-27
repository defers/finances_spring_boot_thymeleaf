package com.defers.springboot.finances.controllers;

import com.defers.springboot.finances.dao.AccountRepo;
import com.defers.springboot.finances.dao.CategoryRepo;
import com.defers.springboot.finances.dao.CurrencyRepo;
import com.defers.springboot.finances.dao.TransactionRepo;
import com.defers.springboot.finances.entity.Account;
import com.defers.springboot.finances.entity.Category;
import com.defers.springboot.finances.entity.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/")
public class MainConroller {

    @Autowired
    private CurrencyRepo currencyRepo;
    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private TransactionRepo transactionRepo;

    @GetMapping("/")
    String getMainPage() {
        return "MainPageBoot";
    }

    @GetMapping("/currencys")
    String getCurrencys(Model model) {

        Iterable<Currency> currencys = currencyRepo.findAll();
        model.addAttribute("currencys", currencys);

        return "Currencys";
    }

    @GetMapping("/accounts")
    String getAccounts(Model model) {

        Iterable<Account> accounts = accountRepo.findAll();
        model.addAttribute("accounts", accounts);

        return "Accounts";
    }

    @GetMapping("/categorys")
    String getCategorys(Model model) {

        Iterable<Category> categorys = categoryRepo.findAll();
        model.addAttribute("categorys", categorys);

        return "Categorys";
    }

    @GetMapping("/currencys/edit")
    String getCurrencyEdit(@RequestParam(required = false) Long id, Model model) {

        if (id == null) {
            model.addAttribute("currency", new Currency());
            return "CurrencyEdit";
        }

        Optional<Currency> currencyOpt = currencyRepo.findById(id);

        if (currencyOpt.isPresent()) {
            Currency currency = currencyOpt.get();
            model.addAttribute("currency", currency);
        } else {
            model.addAttribute("currency", new Currency());
        }

        return "CurrencyEdit";
    }

    @GetMapping("/accounts/edit")
    String getAccountEdit(@RequestParam(required = false) Long id, Model model) {

        Iterable<Currency> currencys = currencyRepo.findAll();
        model.addAttribute("currencys", currencys);

        if (id == null) {
            model.addAttribute("account", new Account("", new Currency()));
            return "AccountEdit";
        }

        Optional<Account> accountOpt = accountRepo.findById(id);

        if (accountOpt.isPresent()) {
            Account account = accountOpt.get();
            model.addAttribute("account", account);
        } else {
            model.addAttribute("account", new Account());
        }

        return "AccountEdit";
    }

    @GetMapping("/categorys/edit")
    String getCategoryEdit(@RequestParam(required = false) UUID uid, Model model) {

        Iterable<Category> categorys = categoryRepo.findAll();
        model.addAttribute("categorys", categorys);

        if (uid == null) {
            model.addAttribute("category", new Category());
            return "CategoryEdit";
        }

        Optional<Category> categoryOpt = categoryRepo.findByUid(uid);

        if (categoryOpt.isPresent()) {
            Category category = categoryOpt.get();
            model.addAttribute("category", category);
            Category parent = category.getParent();
            if (parent == null) {
                model.addAttribute("parentUid", UUID.randomUUID());
            }
            else {
                model.addAttribute("parentUid", parent.getUid());
            }
        } else {
            model.addAttribute("category", new Category());
        }



        return "CategoryEdit";
    }

    @GetMapping("/currencys/delete")
    String getCurrencyDelete(@RequestParam(required = true) Long id, Model model) {

        Optional<Currency> currencyOpt = currencyRepo.findById(id);

        if (currencyOpt.isPresent()) {
            Currency currency = currencyOpt.get();
            currencyRepo.delete(currency);
        }

        return "redirect:/currencys";
    }

    @GetMapping("/accounts/delete")
    String getAccountDelete(@RequestParam(required = true) Long id, Model model) {

        Optional<Account> accountOpt = accountRepo.findById(id);

        if (accountOpt.isPresent()) {
            Account account = accountOpt.get();
            accountRepo.delete(account);
        }

        return "redirect:/accounts";
    }

    @GetMapping("/categorys/delete")
    String getCurrencyDelete(@RequestParam(required = true) UUID id, Model model) {

        Optional<Category> categoryOpt = categoryRepo.findByUid(id);

        if (categoryOpt.isPresent()) {
            Category category = categoryOpt.get();
            categoryRepo.delete(category);
        }

        return "redirect:/categorys";
    }

    @PostMapping("/currencys/edit")
    String saveCurrencyEdit(@RequestParam(required = false) Long id,
                            @RequestParam(required = false) String name,
                            Model model) {

        if (id == null) {
            id = -1L;
        }

        Optional<Currency> currencyOpt = currencyRepo.findById(id);
        Currency currency;

        if (currencyOpt.isPresent()) {
            currency = currencyOpt.get();
            currency.setName(name);
        } else {
            currency = new Currency(name);
        }

        currencyRepo.save(currency);

        model.addAttribute("currency", currency);

        return "CurrencyEdit";
    }

    @PostMapping("/accounts/edit")
    String saveAccountEdit(@RequestParam(required = false) Long id,
                           @RequestParam(required = false) String name,
                           @RequestParam(required = false) Long currencyId,
                           Model model) {

        if (id == null) {
            id = -1L;
        }

        Optional<Account> accountOpt = accountRepo.findById(id);
        Account account;

        Optional<Currency> currencyOpt = currencyRepo.findById(currencyId);
        Currency currency = null;

        if (currencyOpt.isPresent()) {
            currency = currencyOpt.get();
        }

        if (accountOpt.isPresent()) {
            account = accountOpt.get();
            account.setName(name);
            account.setCurrency(currency);
        } else {
            account = new Account(name, currency);
        }

        accountRepo.save(account);

        model.addAttribute("account", account);

        Iterable<Currency> currencys = currencyRepo.findAll();
        model.addAttribute("currencys", currencys);

        return "AccountEdit";
    }

    @PostMapping("/categorys/edit")
    String saveCategoryEdit(@RequestParam(required = false) UUID uid,
                           @RequestParam(required = false) String name,
                           @RequestParam(required = false) UUID parentId,
                           Model model) {

        if (uid == null) {
            uid = new UUID(-1,-1);
        }

        Optional<Category> categoryOpt = categoryRepo.findByUid(uid);
        Category category;

        if (categoryOpt.isPresent()) {
            category = categoryOpt.get();
            category.setName(name);
        } else {
            category = new Category(name);
        }

        if (parentId != null) {
            Optional<Category> parentOpt = categoryRepo.findByUid(parentId);
            Category parent = null;

            if (parentOpt.isPresent()) {
                parent = parentOpt.get();
                category.setParent(parent);
            }
        }

        categoryRepo.save(category);

        model.addAttribute("category", category);

        Category parent = category.getParent();
        if (parent == null) {
            model.addAttribute("parentUid", UUID.randomUUID());
        }
        else {
            model.addAttribute("parentUid", parent.getUid());
        }

        Iterable<Category> categorys = categoryRepo.findAll();
        model.addAttribute("categorys", categorys);

        return "CategoryEdit";
    }

}
