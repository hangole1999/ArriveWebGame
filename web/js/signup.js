var confirm = document.getElementById("signup-form-confirm");
var password = $('#signup-form-password');
var confirm = $('#signup-form-confirm');

confirm.change(function(e) {
    if (password.val() == confirm.val()) {
        
    }
});

password.change(function(e) {
    if (confirm.val() == password.val()) {
        
    }
});