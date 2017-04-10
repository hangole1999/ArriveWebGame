/**
 * Created by BeINone on 2017-04-06.
 */

var signup = document.getElementById("signup__a");
var modal = document.getElementById("signup-modal");
var dialog = modal.getElementsByClassName("modal-dialog")[0];

// open the modal
signup.onclick = function(e) {
    modal.style.display = "block";
    modal.onclick = function() {
        modal.style.display = "none";
    }
};

// close the modal when click outside of dialog only
dialog.onclick = function(e) {
    e.stopPropagation();
};