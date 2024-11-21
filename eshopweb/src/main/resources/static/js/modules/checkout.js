(function() {

    if (document.getElementById('shippingStateRecalculate') !== null) {
        document.getElementById('shippingStateRecalculate').onchange = function() {
            recalculateCheckout();
        };
    }

    if (document.getElementById('shippingVat') !== null) {
        document.getElementById('shippingVat').onchange = function() {
            recalculateCheckout();
        };
    }

    function recalculateCheckout() {
        var spinHandle1 = loadingOverlay.activate();

        var username = document.getElementById("principalUsername").value;
        var shippingState = document.getElementById("shippingStateRecalculate").value;
        var shippingVat = document.getElementById("shippingVat").value;

        var params = {username: username, shippingState: shippingState, shippingVat: shippingVat};

        $.ajax({
            type: "GET",
            contentType: "text/plain",
            url: "/ajax/checkout",
            data: params,
            cache: false,
            timeout: 600000,
            success: function (response) {
                document.getElementById("ajax-order-summary").innerHTML = response;
                document.getElementById("sumTotalOrderWithShippingAndIncludeVat").innerHTML = document.getElementById("orderTotal").innerHTML;

                var shoppingIncluded = document.getElementById("shipping-included-dhl").innerHTML;
                if (shoppingIncluded !== "n/a") {
                    document.getElementById("checkoutTariffZone").style.visibility = 'hidden';
                } else {
                    document.getElementById("checkoutTariffZone").style.visibility = 'visible';
                }
            },
            error: function (e) {
                console.log(e);
            }
        });

        setTimeout(function() {
            loadingOverlay.cancel(spinHandle1);
        }, 1500);

        return false;
    }
})();
