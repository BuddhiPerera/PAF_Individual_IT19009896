$(document).ready(function () {
    adminLoadBuyer();
});

var selectedRow = null;

function adminLoadBuyer() {

    var ajaxConfig = {
        url: "http://localhost:8060/USER/rest1/admin/buyer",
        method: "GET",
        async: 'json'
    }
    var i = 0;
    $.ajax(ajaxConfig).done(function (buyer, status, jQXHB) {
        for (var i = 0; i < buyer.length; i++) {
            var html = '<tr>' +
                '<td style="visibility: hidden">' + buyer[i].id + '</td>' +
                '<td>' + buyer[i].fName + '</td>' +
                '<td>' + buyer[i].lName + '</td>' +
                '<td>' + buyer[i].email + '</td>' +
                '<td>' + buyer[i].contactNo + '</td>' +
                '<td>' + buyer[i].address + '</td>' +
                '<td>' + buyer[i].zipCode + '</td>' +
                '<td>' + buyer[i].password + '</td>' +
            '<td><i class="fa fa-trash"></i></td>' +
            '</tr>';
            $("#datatable-buttons tbody").append(html);
        }
    }).fail(function (jqXHB, status, error) {
        console.log(error)
    })
}

$("#datatable-buttons tbody").on('click', "tr td:last-child", function (eventData) {
    var row = $(this).parents("tr");
    eventData.stopPropagation();
    if (confirm("Are you sure whether you want delete this buyer?")) {
        $.ajax({
            method: 'DELETE',
            url: "http://localhost:8060/USER/rest1/admin/deleteBuyer/" + row.find('td:first-child').text(),
            async: true
        }).done(function (response, status, jqXHR) {
            row.remove();
        }).fail(function (jqXHR, status, error) {
            console.log(error);
        });
    }
});