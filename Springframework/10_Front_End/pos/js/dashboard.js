let salesChartInstance = null;
$(document).ready(function () {
    loadCustomerCount();
    loadItemCount();
    loadSalesData();
});


function loadCustomerCount() {
    $.ajax({
        url: "http://localhost:8080/api/v1/customer",
        method: "GET",
        success: function (data) {
            $("#customerCount").text(data.length);
        }
    });
}


function loadItemCount() {
    $.ajax({
        url: "http://localhost:8080/api/v1/item",
        method: "GET",
        success: function (data) {
            $("#itemCount").text(data.length);
        }
    });
}


function loadSalesData() {

    $.ajax({
        url: "http://localhost:8080/api/v1/order",
        method: "GET",
        success: function (orders) {


            orders.sort((a, b) => a.orderId.localeCompare(b.orderId));

            if (orders.length === 0) {
                $("#totalEarnings").text("0.00");
                return;
            }

            let labels = new Array(orders.length);
            let totals = new Array(orders.length);
            let grandTotal = 0;
            let completed = 0;

            orders.forEach((o, index) => {

                $.ajax({
                    url: `http://localhost:8080/api/v1/order/${o.orderId}`,
                    method: "GET",
                    success: function (details) {

                        let orderTotal = 0;

                        details.forEach(d => {
                            orderTotal += d.qty * d.unitPrice;
                        });

                        let formattedId = o.orderId;

                        if (!formattedId.startsWith("O")) {
                            formattedId = "O" + String(o.orderId).padStart(3, "0");
                        }


                        labels[index] = formattedId;
                        totals[index] = orderTotal;

                        grandTotal += orderTotal;
                        completed++;

                        if (completed === orders.length) {
                            $("#totalEarnings").text(grandTotal.toFixed(2));
                            renderChart(labels, totals);
                        }
                    }
                });

            });

        }
    });
}


function renderChart(labels, data) {


    if (salesChartInstance !== null) {
        salesChartInstance.destroy();
    }

    salesChartInstance = new Chart(document.getElementById("salesChart"), {
        type: "bar",
        data: {
            labels: labels,
            datasets: [{
                label: "Order Amount (Rs)",
                data: data,
                backgroundColor: "#1e88e5"
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
}