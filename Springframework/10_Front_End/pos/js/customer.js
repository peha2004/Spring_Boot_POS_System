function saveCustomer() {

    let cName = $('#customerName').val();
    let cAddress = $('#customerAddress').val();

    console.log(cName, cAddress);

    $.ajax({
        url: "http://localhost:8080/api/v1/customer",
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify({
            cId: 0,
            cName: cName,
            cAddress: cAddress
        }),
        success: function () {
            alert("Saved!");
            loadCustomers();
            $("#customerId").val("");
            $("#customerName").val("");
            $("#customerAddress").val("");
        },
        error: function (err) {
            if(err.responseJSON){
                alert(err.responseJSON.message);
            }else{
                alert("Error");
            }
        }
    });
}

$(document).ready(function () {
    loadCustomers();
});

function loadCustomers() {
    $("#customerTable tbody").empty();

    $.ajax({
        url: "http://localhost:8080/api/v1/customer",
        method: "GET",
        success: function (customers) {
            customers.forEach(c => {
                let row = `<tr>
                    <td>${c.cId}</td>
                    <td>${c.cName}</td>
                    <td>${c.cAddress}</td>
                </tr>`;
                $("#customerTable tbody").append(row);
            });
        }
    });
}

$("#deleteCustomer").click(function () {
    let id = parseInt($('#customerId').val());

    $.ajax({
        url: `http://localhost:8080/api/v1/customer/${id}`,
        method: "DELETE",
        success: function () {
            alert("Deleted");
            loadCustomers();
            $("#customerId").val("");
            $("#customerName").val("");
            $("#customerAddress").val("");
        }
    });
});

$("#updateCustomer").click(function () {
    $.ajax({
        url: "http://localhost:8080/api/v1/customer",
        method: "PUT",
        contentType: "application/json",
        data: JSON.stringify({
            cId: parseInt($('#customerId').val()),
            cName: $('#customerName').val(),
            cAddress: $('#customerAddress').val()
        }),
        success: function () {
            alert("Updated");
            loadCustomers();
            $("#customerId").val("");
            $("#customerName").val("");
            $("#customerAddress").val("");
        }
    });
});

$(document).on('click', '#customerTable tr', function () {
    let id = $(this).find("td:eq(0)").text();
    let name = $(this).find("td:eq(1)").text();
    let address = $(this).find("td:eq(2)").text();

    $('#customerId').val(id);
    $('#customerName').val(name);
    $('#customerAddress').val(address);
});
