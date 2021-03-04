package com.core.controller;

import com.core.model.Currency;
import com.core.model.Role;
import com.core.model.User;
import com.core.service.RoleService;
import com.core.service.UserService;
import com.core.service.currency.CurrencyConversionService;
import java.time.LocalDate;
import java.util.Set;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class WelcomeController {
    private final UserService userService;
    private final RoleService roleService;
    private final CurrencyConversionService currencyService;
    
    @GetMapping("/inject")
    public String dataInjector() {
        Role user = new Role();
        user.setRoleTitle(Role.RoleTitle.USER);
        roleService.save(user);
        Role admin = new Role();
        admin.setRoleTitle(Role.RoleTitle.ADMIN);
        roleService.save(admin);
        User user1 = new User();
        user1.setName("Oleh");
        user1.setPassword("1234");
        user1.setPhoneNumber("4321");
        user1.setDateOfBirth(LocalDate.of(1992, 8, 19));
        user1.setRoles(Set.of(admin));
        userService.saveOrUpdate(user1);
        return "INJECTED";
    }
    
    @GetMapping
    public String getWelcomeMassage() {
        return "Welcome to our bank!";
    }
    
    @GetMapping("convert")
    public String testExchangeAPI(@RequestParam double amount) {
        return currencyService.convert(Currency.USD, Currency.UAH, amount) + "";
    }
}
