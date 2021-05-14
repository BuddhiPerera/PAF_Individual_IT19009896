$(document).ready(function () {
    loadResearcher();
});
var selectedRow = null;
function loadResearcher() {

    var ajaxConfig = {
        url: "http://localhost:8060/USER/rest1/researcher/getResearcher",
        method: "GET",
        async: 'json'
    }
    var i = 0;
    $.ajax(ajaxConfig).done(function (researcher, status, jQXHB) {
        document.getElementById("id").value = researcher.id;
        document.getElementById("first-name").value = researcher.fName;
        document.getElementById("last-name").value = researcher.lName;
        document.getElementById("email").value = researcher.email;
        document.getElementById("contactNo").value = researcher.contactNo;
        document.getElementById("address").value = researcher.address;
        document.getElementById("zipcode").value = researcher.zipCode;
        document.getElementById("rate").value = researcher.rate;
    }).fail(function (jqXHB, status, error) {
        console.log(error)
    })
}

$("#btnUpdate").click(function () {

    var researcher = {
        fName: $("#first-name").val(),
        lName: $("#last-name").val(),
        email: $("#email").val(),
        contactNo: $("#contactNo").val(),
        address: $("#address").val(),
        zipCode: $("#zipcode").val(),
        rate: $("#rate").val()
    };
    var ajaxConfig = {
        method: 'PUT',
        url: 'http://localhost:8060/USER/rest1/researcher/update',
        async: true,
        contentType: 'application/json',
        data: JSON.stringify(researcher)
    };
    $.ajax(ajaxConfig).done(function (response, status, jqXHR) {
        window.location.reload();
        alert("Updated Successfully!");
    }).fail(function (jqXHR, status, error) {
        console.log(error);
    }).always(function () {
        $("#btnClear").click();
    });
});

$("#btnDelete").click(function () {

    if (confirm("Are you sure whether you want delete this Item?")) {
        $.ajax({
            method: 'DELETE',
            url: 'http://localhost:8060/USER/rest1/researcher/delete/' +  $("#id").val(),
            async: true
        }).done(function (response, status, jqXHR) {
            alert("Deleted Successfully!!")
        }).fail(function (jqXHR, status, error) {
            console.log(error);
        });
    }
});