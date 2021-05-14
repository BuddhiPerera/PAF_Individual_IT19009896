
// $("#datatable tbody").on('click', "tr td:last-child", function (eventData) {
//     var row = $(this).parents("tr");
//     eventData.stopPropagation();
//     if (confirm("Are you sure whether you want delete this Item?")) {
//         $.ajax({
//             method: 'DELETE',
//             url: 'http://localhost:8081/USER/rest1/buyer/delete/' + row.find('td:first-child').text(),
//             async: true
//         }).done(function (response, status, jqXHR) {
//             row.remove();
//         }).fail(function (jqXHR, status, error) {
//             console.log(error);
//         });
//     }
// });

//button id
$("#btnsubmit").click(function () {

    var buyer = {
        // purple == DTO
        // yellow == HTML id
        fName: $("#fName").val(),
        lName: $("#lName").val(),
        email: $("#email").val(),
        contactNo: $("#contact").val(),
        address: $("#add").val(),
        zipCode: $("#zip").val(),
        password: $("#password").val()
    };
    alert("Registered Successfully!!")
        var ajaxConfig = {
            method: 'POST',
            url: 'http://localhost:8060/USER/rest1/buyer/saveUser',
            async: true,
            contentType: 'application/json',
            data: JSON.stringify(buyer) // send data to saveUser method in controller
        };
        $.ajax(ajaxConfig).done(function (response, status, jqXHR) {
            window.location.href = "http://localhost:8090/Payment/allProducts.jsp?"+response;
            $("#id").focus();
        }).fail(function (jqXHR, status, error) {
            console.log(error);
        });
});

// $("#datatable tbody").on('click', 'tr', function () {
//     selectedRow = $(this);
//     $("#id").val($(this).find("td:first-child").text());
//     $("#fName").val($(this).find("td:nth-child(2)").text());
//     $("#lName").val($(this).find("td:nth-child(3)").text());
//     $("#email").val($(this).find("td:nth-child(4)").text());
//     $("#contactNo").val($(this).find("td:nth-child(5)").text());
//     $("#address").val($(this).find("td:nth-child(6)").text());
//     $("#zipcode").val($(this).find("td:nth-child(7)").text());
//
//
//
//     $("#id").attr("disabled", 'true');
//     $("#datatable tbody tr").removeClass('row-selected');
//     selectedRow.addClass('row-selected');
// });

