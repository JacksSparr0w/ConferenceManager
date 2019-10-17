function checkLogin(input) {
    if(!/^[a-zA-Z][a-zA-Z0-9-_\.]{1,20}$/.test(input.value)){
        input.style.border = "2px solid #ed1d12";
    }else{
        input.style.border = "none";
    }
}

function checkPassword(input) {
    if(!/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).*$/.test(input.value)){
        input.style.border = "2px solid #ed1d12";
    }else{
        input.style.border = "none";
    }
}


function checkName(input) {

    if (!/^[A-ZА-Я]?[a-zа-я]{1,32}$/.test(input.value)) {
        input.style.border = "2px solid #ed1d12";
    } else {
        input.style.border = "none";
    }
}

function checkEmail(input) {

    if (!/^[-\w.]+@([A-z0-9][-A-z0-9]+\.)+[A-z]{2,4}$/.test(input.value)) {
        input.style.border = "2px solid #ed1d12";
    } else {
        input.style.border = "none";
    }
}

function checkNumber(input) {

    if (!/^\d{1,4}$/.test(input.value)) {
        input.style.border = "2px solid #ed1d12";
    } else {
        input.style.border = "none";
    }
}

function checkNotNull(input) {

    if (!/^.+$/.test(input.value)) {
        input.style.border = "2px solid #ed1d12";
    } else {
        input.style.border = "none";
    }
}


