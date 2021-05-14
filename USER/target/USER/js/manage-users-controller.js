$(document).ready(function () {
    loadUser();
});
var selectedRow = null;
function loadUser() {

    var ajaxConfig = {
        url: "http://localhost/:8060/USER/rest1/Login/profile",
        method: "GET",
        async: 'json'
    }
    var i = 0;
    $.ajax(ajaxConfig).done(function (product, status, jQXHB) {
        for (var i = 0; i < product.length; i++) {
            var html = '<tr>' +
                '<td>' + product[i].id + '</td>' +
                '<td>' + product[i].name + '</td>' +
                '<td style="width: 5%">' + product[i].description + '</td>' +
                '<td>' + product[i].price + '</td>' +
                '<td>' + product[i].qty + '</td>' +
                '<td>' + product[i].shipping + '</td>' +
                '<td >' + '<img style="height: 35px; width: 35px;" src=' + product[i].image + '/>' + '</td>' +
                '<td><i class="fas fa-trash"></i></td>' +
                '</tr>';
            $("#datatable tbody").append(html);
        }
        console.log(product)
    }).fail(function (jqXHB, status, error) {
        console.log(error)
    })
}

$("#datatable tbody").on('click', "tr td:last-child", function (eventData) {
    var row = $(this).parents("tr");
    eventData.stopPropagation();
    if (confirm("Are you sure whether you want delete this Item?")) {
        $.ajax({
            method: 'DELETE',
            url: 'http://localhost/Product/rest/products/delete/' + row.find('td:first-child').text(),
            async: true
        }).done(function (response, status, jqXHR) {
            row.remove();
        }).fail(function (jqXHR, status, error) {
            console.log(error);
        });
    }
});

$("#btnsubmit").click(function () {
    var product = {
        id: $("#id").val(),
        name: $("#name").val(),
        description: $("#description").val(),
        price: $("#price").val(),
        qty: $("#qty").val(),
        shipping: $("#shipping").val(),
        image: $("#image").val()
    };
    if (!selectedRow) {
        var ajaxConfig = {
            method: 'POST',
            url: 'http://localhost/Product/rest/products/save/',
            async: true,
            contentType: 'application/json',
            data: JSON.stringify(product)
        };
        $.ajax(ajaxConfig).done(function (response, status, jqXHR) {
            var html = "<tr>" +
                "<td>" + product.id + "</td>" +
                "<td>" + product.name + "</td>" +
                "<td>" + product.description + "</td>" +
                "<td>" + product.price + "</td>" +
                "<td>" + product.qty + "</td>" +
                "<td>" + product.shipping + "</td>" +
                "<td>" + product.image + "</td>" +
                '<td><i class="fa fa-trash"></i></td>' +
                "</tr>";
            $("#datatable tbody").append(html);
            $("#id, #name, #description, #price,#qty,#shipping,#image").val("");
            $("#id").focus();
        }).fail(function (jqXHR, status, error) {
            console.log(error);
        });
    } else {
        var ajaxConfig = {
            method: 'PUT',
            url: 'http://localhost/Product/rest/products/update/',
            async: true,
            contentType: 'application/json',
            data: JSON.stringify(product)
        };
        $.ajax(ajaxConfig).done(function (response, status, jqXHR) {
            selectedRow.find("td:nth-child(1)").text(product.id);
            selectedRow.find("td:nth-child(2)").text(product.name);
            selectedRow.find("td:nth-child(3)").text(product.description);
            selectedRow.find("td:nth-child(4)").text(product.price);
            selectedRow.find("td:nth-child(5)").text(product.qty);
            selectedRow.find("td:nth-child(6)").text(product.shipping);
            selectedRow.find("td:nth-child(7)").text(product.image).src;


        }).fail(function (jqXHR, status, error) {
            console.log(error);
        }).always(function(){
            $("#btnClear").click();
        });
    }


});


$("#datatable tbody").on('click', 'tr', function () {
    selectedRow = $(this);
    $("#id").val($(this).find("td:first-child").text());
    $("#name").val($(this).find("td:nth-child(2)").text());
    $("#description").val($(this).find("td:nth-child(3)").text());
    $("#price").val($(this).find("td:nth-child(4)").text());
    $("#qty").val($(this).find("td:nth-child(5)").text());
    $("#shipping").val($(this).find("td:nth-child(6)").text());
    $("#image").val($(this).find("td:nth-child(7)").getAttribute().src);



    $("#id").attr("disabled", 'true');
    $("#datatable tbody tr").removeClass('row-selected');
    selectedRow.addClass('row-selected');
});

function initializePagination(totalElements) {

    var totalPages = parseInt(totalElements / 5 + (((totalElements % 5) !== 0) ? 1 : 0));
    $(".page-item").remove();

    var html = '<li class="page-item"><a class="page-link" href="javascript:void(0)">&laquo;</a></li>';

    for (var i = 0; i < totalPages; i++) {
        html += '<li class="page-item"><a class="page-link" href="#">' + (i + 1) + '</a></li>'
    }

    html += '<li class="page-item"><a class="page-link" href="#">&raquo;</a></li>';

    $(".card-footer .pagination").html(html);

    $(".card-footer .pagination .page-item:first-child").click(function () {
        loadProducts(0);
    });

    $(".card-footer .pagination .page-item:last-child").click(function () {
        loadProducts(totalPages - 1);
    });

    $(".card-footer .pagination .page-item").click(function () {
        var number = parseInt($(this).find("a").text());
        if (number) {
            loadProducts(number - 1);
        }
    })
}
