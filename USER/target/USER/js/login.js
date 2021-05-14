$(document).ready(function () {
    loadUser();
});
var selectedRow = null;
function loadUser() {

    var ajaxConfig = {
        url: "http://localhost/:8060/USER/rest1/Login/log",
        method: "GET",
        async: 'json'
    }
    var i = 0;
    $.ajax(ajaxConfig).done(function (users, status, jQXHB) {
        for (var i = 0; i < users.length; i++) {
        }
        console.log(users)
    }).fail(function (jqXHB, status, error) {
        console.log(error)
    })
}