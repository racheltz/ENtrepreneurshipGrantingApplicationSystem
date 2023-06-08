package com.harrisonwells.funding.views;

import com.harrisonwells.funding.backend.models.UserEntity;
import com.harrisonwells.funding.backend.services.UserService;
import com.harrisonwells.funding.models.RegisteredUser;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.*;
import com.vaadin.flow.data.validator.StringLengthValidator;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

import java.util.ArrayList;
import java.util.List;


@Route(value = "register", layout = HomeLayout.class)
@PageTitle("Register")
@AnonymousAllowed
public class RegisterView extends VerticalLayout {

    private final FormLayout formLayout;
    private final TextField firstName = new TextField("First Name:");
    private final TextField lastName = new TextField("Last Name:");
    private final TextField username = new TextField("Username:");
    private final TextField nationalID = new TextField("National ID:");
    private final TextField contactNumber = new TextField("Contact:");
    private final EmailField email = new EmailField("Email:");
    private final TextField address = new TextField("Address:");
    private final ComboBox<String> category = new ComboBox<>("Category");
    private final PasswordField password = new PasswordField("Password");
    private final PasswordField confirmPassword = new PasswordField("Confirm Password:");

    private final Button submit = new Button("Register");


    public RegisterView(UserService userService) {

        Binder<RegisteredUser> binder = new Binder<>();

        firstName.setRequired(true);
        firstName.setRequiredIndicatorVisible(true);
        firstName.setAllowedCharPattern("^[a-zA-Z]*$");
        firstName.setMaxLength(15);
        binder.forField(firstName)
                .withValidator(new StringLengthValidator("First Name must be between 1 and 15 characters", 1, 50))
                .bind(RegisteredUser::getFirstName, RegisteredUser::setFirstName);

        lastName.setRequired(true);
        lastName.setRequiredIndicatorVisible(true);
        lastName.setAllowedCharPattern("^[a-zA-Z]*$");
        lastName.setMaxLength(15);
        binder.forField(lastName)
                .withValidator(new StringLengthValidator("Last Name must be between 1 and 15 characters", 1, 50))
                .bind(RegisteredUser::getLastName, RegisteredUser::setLastName);

        username.setRequired(true);
        username.setRequiredIndicatorVisible(true);
        username.setAllowedCharPattern("^[a-zA-Z0-9]*$");
        username.setMaxLength(15);
        username.setMinLength(5);
        binder.forField(username)
                .withValidator(new StringLengthValidator("Username must be between 5 and 15 characters", 5, 50))
                .bind(RegisteredUser::getUsername, RegisteredUser::setUsername);

        nationalID.setRequired(true);
        nationalID.setRequiredIndicatorVisible(true);
        nationalID.setAllowedCharPattern("^[0-9]*$");
        nationalID.setMinLength(20);
        nationalID.setMaxLength(20);
        binder.forField(nationalID)
                .withValidator(new StringLengthValidator("National ID must be 20 characters", 20, 20))
                .bind(RegisteredUser::getNationalID, RegisteredUser::setNationalID);

        contactNumber.setRequired(true);
        contactNumber.setRequiredIndicatorVisible(true);
        contactNumber.setAllowedCharPattern("^[0-9]*$");
        contactNumber.setMinLength(10);
        contactNumber.setMaxLength(10);
        binder.forField(contactNumber)
                .withValidator(new StringLengthValidator("Contact Number must be 10 characters", 10, 10))
                .bind(RegisteredUser::getContactNumber, RegisteredUser::setContactNumber);

        email.setRequired(true);
        email.setRequiredIndicatorVisible(true);
        email.setMinLength(5);
        email.setMaxLength(50);
        binder.forField(email)
                .withValidator(new StringLengthValidator("Email must be between 5 and 50 characters", 5, 50))
                .bind(RegisteredUser::getEmail, RegisteredUser::setEmail);



        category.setRequired(true);
        category.setRequiredIndicatorVisible(true);
        List<String> users = new ArrayList<>();
        users.add("INVESTOR");
        users.add("ENTREPRENEUR");
        category.setItems(users);
        binder.forField(category)
                .asRequired("Category is required")
                .bind(RegisteredUser::getEmail, RegisteredUser::setEmail);


        address.setRequired(true);
        address.setRequiredIndicatorVisible(true);
        address.setAllowedCharPattern("^[a-zA-Z0-9]*$");
        address.setMinLength(5);
        address.setMaxLength(25);
        binder.forField(address)
                .withValidator(new StringLengthValidator("Address must be 5 and 25 characters", 5, 25))
                .bind(RegisteredUser::getAddress, RegisteredUser::setAddress);

        password.setRequired(true);
        password.setRequiredIndicatorVisible(true);
        password.setMinLength(8);
        password.setMaxLength(15);
        binder.forField(password)
                .withValidator(new StringLengthValidator("Address must be 8 and 15 characters", 8, 15))
                .bind(RegisteredUser::getPassword, RegisteredUser::setPassword);

        confirmPassword.setRequired(true);
        confirmPassword.setRequiredIndicatorVisible(true);
        confirmPassword.setMinLength(8);
        confirmPassword.setMaxLength(15);
        binder.forField(confirmPassword)
                .withValidator(new Validator<String>() {
                    @Override
                    public ValidationResult apply(String s, ValueContext valueContext) {
                        if (!s.equals(password.getValue())){
                            return ValidationResult.error("Password must be equal to Confirm Password");
                        }
                        return ValidationResult.ok();
                    }
                })
                .withValidator(new StringLengthValidator("Address must be 8 and 15 characters", 8, 15))
                .bind(RegisteredUser::getConfirmPassword, RegisteredUser::setConfirmPassword);

        submit.addClickListener(buttonClickEvent -> {
            BinderValidationStatus<RegisteredUser> validationResult = binder.validate();
            if (validationResult.isOk()) {

                if (userService.isUsernameExists(username.getValue())){
                    username.setErrorMessage("Username already exists");
                    return;
                }

                if (userService.isEmailExists(email.getValue())){
                    username.setErrorMessage("Email already exists");
                    return;
                }


                UserEntity user = UserEntity.builder()
                        .active(false)
                        .userName(username.getValue())
                        .email(email.getValue())
                        .password(password.getValue())
                        .nationalId(nationalID.getValue())
                        .address(address.getValue())
                        .contactNumber(contactNumber.getValue())
                        .role(category.getValue())
                        .build();

                userService.saveUser(user);

                // Form is valid, proceed with saving data
                Notification.show("Registered successful");

                getUI().ifPresent(ui -> ui.navigate("login")); // Redirect to main view

            } else {
                // Form is invalid, handle validation errors
                List<ValidationResult> errors = validationResult.getValidationErrors();
                // Display errors to the user
            }
        });

        formLayout = new FormLayout(firstName, lastName, username, nationalID, contactNumber, email, address, category, password, confirmPassword);
        add(formLayout, submit);
    }
}
