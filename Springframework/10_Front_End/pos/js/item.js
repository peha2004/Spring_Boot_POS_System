$(document).ready(function (){
    loadItems();
    generateItemId();
});

function generateItemId(){
    $.ajax({
        url:"http://localhost:8080/api/v1/item/next-id",
        method:"GET",
        success:function (id){
            $("#itemCode").val(id);
            $("#itemCode").prop("disabled", true);
        }
    });
}
function saveItem(){

    $.ajax({
        url:"http://localhost:8080/api/v1/item",
        method:"POST",
        contentType:"application/json",
        data: JSON.stringify({
            itemCode: $('#itemCode').val(),
            description: $('#description').val(),
            unitPrice: parseFloat($('#unitPrice').val()),
            qty: parseInt($('#qty').val())
        }),
        success:function (){
            alert("Saved!");
            loadItems();

            // clear inputs
            $("#description").val("");
            $("#unitPrice").val("");
            $("#qty").val("");


            generateItemId();
        },
        error:function (err){
            if(err.responseJSON){
                alert(err.responseJSON.message);
            }else{
                alert("Error");
            }
        }
    });
}

function loadItems(){
    $("#itemTable tbody").empty();

    $.ajax({
        url:"http://localhost:8080/api/v1/item",
        method:"GET",
        success:function (items){
            items.forEach(i => {
                let row = `<tr>
                    <td>${i.itemCode}</td>
                    <td>${i.description}</td>
                    <td>${i.unitPrice}</td>
                    <td>${i.qty}</td>
                </tr>`;
                $("#itemTable tbody").append(row);
            });
        }
    });
}

$("#deleteItem").click(function (){
    let code = $('#itemCode').val();

    $.ajax({
        url:`http://localhost:8080/api/v1/item/${code}`,
        method:"DELETE",
        success:function (){
            alert("Deleted!");
            loadItems();

            $("#description").val("");
            $("#unitPrice").val("");
            $("#qty").val("");

            generateItemId();
        }
    });
});

$("#updateItem").click(function (){
    $.ajax({
        url:"http://localhost:8080/api/v1/item",
        method:"PUT",
        contentType:"application/json",
        data: JSON.stringify({
            itemCode: $('#itemCode').val(),
            description: $('#description').val(),
            unitPrice: parseFloat($('#unitPrice').val()),
            qty: parseInt($('#qty').val()
            )}),
        success:function (){
            alert("Updated!");
            loadItems();

            $("#itemCode").prop("disabled", true);
            $("#description").val("");
            $("#unitPrice").val("");
            $("#qty").val("");

            generateItemId();
        }
    });
});


$(document).on('click','#itemTable tr',function (){
    $('#itemCode').val($(this).find("td:eq(0)").text());
    $('#description').val($(this).find("td:eq(1)").text());
    $('#unitPrice').val($(this).find("td:eq(2)").text());
    $('#qty').val($(this).find("td:eq(3)").text());
});