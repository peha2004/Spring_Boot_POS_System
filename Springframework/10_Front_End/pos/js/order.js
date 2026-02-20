
let cart = [];


$(document).ready(function () {
    loadCustomers();
    loadItems();
    generateOrderIdFromDB();

    let today = new Date().toISOString().split('T')[0];
    $("#date").val(today);
});

function generateOrderIdFromDB(){
    $.ajax({
        url:"http://localhost:8080/api/v1/order/next-id",
        method:"GET",
        success:function (id){
            $("#orderId").val(id);
            $("#orderId").prop("disabled", true);
        }
    });
}


function loadCustomers() {
    $("#orderCustomer").empty();

    $.ajax({
        url: "http://localhost:8080/api/v1/customer",
        method: "GET",
        success: function (data) {
            data.forEach(c => {
                $("#orderCustomer").append(
                    `<option value="${c.cId}">${c.cName}</option>`
                );
            });
        }
    });
}

function loadItems() {
    $("#orderItem").empty();

    $.ajax({
        url: "http://localhost:8080/api/v1/item",
        method: "GET",
        success: function (data) {
            data.forEach(i => {
                $("#orderItem").append(
                    `<option value="${i.itemCode}" data-price="${i.unitPrice}">
                        ${i.description} (Stock: ${i.qty})
                    </option>`
                );
            });
        }
    });
}

$("#btnAddToCart").click(function () {

    let itemCode = $("#orderItem").val();
    let qty = parseInt($("#orderQty").val());
    let price = parseFloat($("#orderItem option:selected").data("price"));

    if (!itemCode) {
        alert("Select item");
        return;
    }

    if (qty <= 0) {
        alert("Invalid quantity");
        return;
    }

    cart.push({
        itemCode: itemCode,
        qty: qty,
        unitPrice: price
    });

    loadCart();
});


function loadCart() {
    $("#cartTable").empty();

    let total = 0;

    cart.forEach((i, index) => {

        let subtotal = i.qty * i.unitPrice;
        total += subtotal;

        let row = `
            <tr>
                <td>${i.itemCode}</td>
                <td>${i.qty}</td>
                <td>${i.unitPrice.toFixed(2)}</td>
                <td>${subtotal.toFixed(2)}</td>
                <td>
                    <button class="btn btn-danger btn-sm" onclick="removeItem(${index})">
                        Remove
                    </button>
                </td>
            </tr>
        `;

        $("#cartTable").append(row);
    });

    $("#orderTotal").text(total.toFixed(2));
}


function removeItem(index) {
    cart.splice(index, 1);
    loadCart();
}


$("#btnPlaceOrder").click(function () {

    if (cart.length === 0) {
        alert("Cart is empty");
        return;
    }

    let order = {
        orderId: $("#orderId").val(),
        customerId: $("#orderCustomer").val(),
        date: $("#date").val(),
        items: cart
    };

    $.ajax({
        url: "http://localhost:8080/api/v1/order",
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify(order),

        success: function () {
            alert("Order Placed Successfully!");

            cart = [];
            loadCart();
            loadItems();
            generateOrderIdFromDB();
        },
        error: function (err) {
            if(err.responseJSON){
                alert(err.responseJSON.message);
            }else{
                alert("Error placing order");
            }
        }
    });
});