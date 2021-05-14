$(document).ready(function () {
    adminLoadResearcher();
});

var selectedRow = null;

function adminLoadResearcher() {

    var ajaxConfig = {
        url: "http://localhost:8060/USER/rest1/admin/researcher",
        method: "GET",
        async: 'json'
    }
    var i = 0;
    $.ajax(ajaxConfig).done(function (researcher, status, jQXHB) {
        for (var i = 0; i < researcher.length; i++) {
            var html = '<tr>' +
                '<td style="visibility: hidden">' + researcher[i].id + '</td>' +
                '<td>' + researcher[i].fName + '</td>' +
                '<td>' + researcher[i].lName + '</td>' +
                '<td>' + researcher[i].email + '</td>' +
                '<td>' + researcher[i].contactNo + '</td>' +
                '<td>' + researcher[i].address + '</td>' +
                '<td>' + researcher[i].zipCode + '</td>' +
                '<td>' + researcher[i].rate + '</td>' +
                '<td>' + researcher[i].password + '</td>' +
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
    if (confirm("Are you sure whether you want delete this Researcher?")) {
        $.ajax({
            method: 'DELETE',
            url: "http://localhost:8060/USER/rest1/admin/delete/" + row.find('td:first-child').text(),
            async: true
        }).done(function (response, status, jqXHR) {
            row.remove();
        }).fail(function (jqXHR, status, error) {
            console.log(error);
        });
    }
});