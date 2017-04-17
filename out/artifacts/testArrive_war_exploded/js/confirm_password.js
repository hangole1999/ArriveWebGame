var password = document.getElementById('signup-form-password');
var confirm = document.getElementById('signup-form-confirm');
var confirmIcon = document.getElementById('signup-form-confirm__i');

function confirmPassword() {
    if (password.value == confirm.value && password.value.length != 0) {
        validStatus();
    } else {
        invalidStatus();
    }
}

function validStatus() {
    confirmIcon.classList.remove('fa-times-circle');
    confirmIcon.classList.add('fa-check-circle');
    confirmIcon.style.color = '#4CAF50';
}

function invalidStatus() {
    confirmIcon.classList.remove('fa-check-circle');
    confirmIcon.classList.add('fa-times-circle');
    confirmIcon.style.color = '#F44336';
}