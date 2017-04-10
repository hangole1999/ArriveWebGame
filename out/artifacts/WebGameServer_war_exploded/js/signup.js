/**
 * Created by BeINone on 2017-04-06.
 */

var signup = document.getElementById("signup__a");
var modal = document.getElementById("signup-modal");
var dialog = modal.getElementsByClassName("modal-dialog")[0];
signup.onclick = function () {
    modal.style.display = "block";
    dialog.style.display = "absolute";
}