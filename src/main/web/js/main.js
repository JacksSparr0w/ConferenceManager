function checkLogin(input) {
    if(!/^[a-zA-Z][a-zA-Z0-9-_\.]{1,20}$/.test(input.value)){
        input.style.border = "2px solid #ed1d12";
        input.title = "login is invalid!";
        setDisabledButtons();
    }else{
        input.style.border = "none";
        input.title = "";
        setEnabledButtons();
    }
}

function checkPassword(input) {
    if(!/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).*$/.test(input.value)){
        input.style.border = "2px solid #ed1d12";
        input.title = "password is invalid!";
        setDisabledButtons();
    }else{
        input.style.border = "none";
        input.title = "";
        setEnabledButtons();
    }
}


function checkName(input) {

    if (!/^[A-ZА-Я]?[a-zа-я]{1,32}$/.test(input.value)) {
        input.style.border = "2px solid #ed1d12";
        input.title = "name is invalid!";
        setDisabledButtons();
    } else {
        input.style.border = "none";
        input.title = "";
        setEnabledButtons();
    }

}

function checkEmail(input) {

    if (!/^[-\w.]+@([A-z0-9][-A-z0-9]+\.)+[A-z]{2,4}$/.test(input.value)) {
        input.style.border = "2px solid #ed1d12";
        input.title = "email is invalid!";
        setDisabledButtons();
    } else {
        input.style.border = "none";
        input.title = "";
        setEnabledButtons();
    }
}

function checkNumber(input) {

    if (!/^\d{1,4}$/.test(input.value)) {
        input.style.border = "2px solid #ed1d12";
        input.title = "It can't be nothing there!";
        setDisabledButtons();
    } else {
        input.style.border = "none";
        input.title = "";
        setEnabledButtons();
    }
}

function checkNotNull(input) {

    if (!/^.+$/.test(input.value)) {
        input.style.border = "2px solid #ed1d12";
        input.title = "It can't be nothing there!";
        setDisabledButtons();
    } else {
        input.style.border = "none";
        input.title = "";
        setEnabledButtons();
    }
}

function checkDateOfBirth(input) {
    var date = Date.parse(input.value);
    if (date > Date.now()){
        input.style.border = "2px solid #ed1d12";
        input.title = "Date of birth can't be in future";
        setDisabledButtons();
    } else {
        input.style.border = "none";
        input.title = "";
        setEnabledButtons();
    }
}

function checkDateOfEvent(input) {
    var date = Date.parse(input.value);
    if (date < Date.now()){
        input.style.border = "2px solid #ed1d12";
        input.title = "Date of event can't be in the past";
        setDisabledButtons();
    } else {
        input.style.border = "none";
        input.title = "";
        setEnabledButtons();
    }
}

function setDisabledButtons() {
    var buttons = document.getElementsByName("submit");
    buttons.forEach(function (value) { value.disabled = true; });
}

function setEnabledButtons() {
    var buttons = document.getElementsByName("submit");
    buttons.forEach(function (value) { value.disabled = false; });

}


