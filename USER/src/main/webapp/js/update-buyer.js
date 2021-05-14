$(document).ready(function () {
    loadBuyer();
});
var selectedRow = null;
function loadBuyer() {

    var ajaxConfig = {
        url: "http://localhost:8060/USER/rest1/buyer/getBuyer",
        method: "GET",
        async: 'json'
    }
    var i = 0;
    $.ajax(ajaxConfig).done(function (buyer, status, jQXHB) {
        document.getElementById("id").value = buyer.id;
        document.getElementById("first-name").value = buyer.fName;
        document.getElementById("last-name").value = buyer.lName;
        document.getElementById("email").value = buyer.email;
        document.getElementById("contactNo").value = buyer.contactNo;
        document.getElementById("address").value = buyer.address;
        document.getElementById("zipcode").value = buyer.zipCode;
    }).fail(function (jqXHB, status, error) {
        console.log(error)
    })
}


$("#btnUpdate").click(function () {

    var buyer = {
        fName: $("#first-name").val(),
        lName: $("#last-name").val(),
        email: $("#email").val(),
        contactNo: $("#contactNo").val(),
        address: $("#address").val(),
        zipCode: $("#zipcode").val(),
        password: $("#zipcode").val()
    };
    var ajaxConfig = {
        method: 'PUT',
        url: 'http://localhost:8060/USER/rest1/buyer/updateBuyer',
        async: true,
        contentType: 'application/json',
        data: JSON.stringify(buyer)
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
            url: 'http://localhost:8060/USER/rest1/buyer/delete/' +  $("#id").val(),
            async: true
        }).done(function (response, status, jqXHR) {
            alert("Deleted Successfully!!")
        }).fail(function (jqXHR, status, error) {
            console.log(error);
        });
    }
});

