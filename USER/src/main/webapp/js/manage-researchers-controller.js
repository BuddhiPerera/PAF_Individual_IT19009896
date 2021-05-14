$("#btnsubmit").click(function () {

    var researcher = {
        // purple == DTO
        // yellow == HTML id
        fName: $("#fName").val(),
        lName: $("#lName").val(),
        email: $("#email").val(),
        contactNo: $("#contact").val(),
        address: $("#address").val(),
        zipCode: $("#zipcode").val(),
        rate: $("#rate").val(),
        password: $("#pass").val()
    };
    alert("Registered Successfully!!")
    var ajaxConfig = {
        method: 'POST',
        url: 'http://localhost:8060/USER/rest1/researcher/saveResearcher',
        async: true,
        contentType: 'application/json',
        data: JSON.stringify(researcher) // send data to saveResearcher method in controller
    };
    $.ajax(ajaxConfig).done(function (response, status, jqXHR) {
        window.location.href = "http://localhost:8090/Payment/allProducts.jsp?"+response;
        $("#id").focus();
    }).fail(function (jqXHR, status, error) {
        console.log(error);
    });
});