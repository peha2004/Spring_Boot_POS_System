$(document).ready(function () {
    loadOrders();
});

function loadOrders() {

    $("#ordersTable").empty();

    $.ajax({
        url: "http://localhost:8080/api/v1/order",
        method: "GET",
        success: function (orders) {

            orders.forEach(o => {


                $.ajax({
                    url: `http://localhost:8080/api/v1/order/${o.orderId}`,
                    method: "GET",
                    success: function (details) {

                        let summary = "";
                        let total = 0;

                        details.forEach(d => {
                            summary += `${d.itemCode} x${d.qty}, `;
                            total += d.qty * d.unitPrice;
                        });

                        let row = `<tr>
                            <td>${o.orderId}</td>
                            <td>${o.date}</td>
                            <td>${o.customerId}</td>
                            <td>${summary}</td>
                            <td>${total.toFixed(2)}</td>
                        </tr>`;

                        $("#ordersTable").append(row);
                    }
                });

            });

        }
    });
}
