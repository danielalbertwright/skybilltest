function slideSection(link, section) {
    $(section).slideToggle();
    var state = $(link).text();
    if(state=="Show") {
      $(link).text("Hide");
    } else {
      $(link).text("Show");
    }
  }

function addLeadingZero(number) {
  if(number < 10) {
    return "0" + number;
  } else {
    return "" + number;
  }
}

function formatDate(dateIn) {
    var d = new Date(dateIn);
    var dateOut = addLeadingZero(d.getDate()) + "/" + addLeadingZero(d.getMonth()+1) + "/" + d.getFullYear();
    return dateOut;
}

function formatCurrency(numberIn) {
    return "&pound;" + numberIn.toFixed(2);
}

function getIconForType(type) {
    var icon = "";
    switch(type) {
        case "tv":
            icon = "<i class=\"fa fa-television\"></i>";
            break;
        case "talk":
            icon = "<i class=\"fa fa-phone\"></i>";
            break;
        case "broadband":
            icon = "<i class=\"fa fa-wifi\"></i>";
            break;
        default:
            break;
    }
    return icon;
}

function getBill(apiPath) {
    // When the page has loaded, fetch the Json and update the page.
    $.get( apiPath, function(json) {
        $(".container").fadeIn();
        $("#statement-date").html(formatDate(json.statement.generated));
        $("#statement-from").html(formatDate(json.statement.period.from));
        $("#statement-to").html(formatDate(json.statement.period.to));
        $("#package-total").html(formatCurrency(json.package.total));
        $("#call-charges-total").html(formatCurrency(json.callCharges.total));
        $("#sky-store-total").html(formatCurrency(json.skyStore.total));
        $("#statement-due").html(formatDate(json.statement.due));
        $("#bill-total").html(formatCurrency(json.total));

        if(json.package.subscriptions.length > 0) {
            for (i = 0; i < json.package.subscriptions.length; i++) {
                var sub = json.package.subscriptions[i]

                var icon = getIconForType(sub.type);

                var newLine = '<tr><td class=\"table-left-col package-type-column\">' + icon + '</td><td>' + sub.name + '</td><td class=\"table-right-col\">' + formatCurrency(sub.cost) + '</td></tr>)'
                $("#package-table").append(newLine);
            }
            $("#package-section").fadeIn("400", function() {
                slideSection("#package-toggle",".package");
            });
        }

        if(json.callCharges.calls.length > 0) {
            for (i = 0; i < json.callCharges.calls.length; i++) {
                var call = json.callCharges.calls[i]
                $("#call-charges-table").append("<tr><td class=\"table-left-col\">" + call.called + "</td><td class=\"table-middle-col\">" + call.duration + "</td><td class=\"table-right-col\">" + formatCurrency(call.cost) + "</td></tr>)");
            }
            $("#call-charges-section").fadeIn();
        }

        if(json.skyStore.rentals.length > 0 || json.skyStore.buyAndKeep.length > 0) {
            if(json.skyStore.rentals.length > 0) {
                for (i = 0; i < json.skyStore.rentals.length; i++) {
                    var rental = json.skyStore.rentals[i]
                    $("#rentals-table").append("<tr><td class=\"table-left-col\">" + rental.title + "</td><td class=\"table-right-col\">" + formatCurrency(rental.cost) + "</td></tr>)");
                }
                $("#rentals-section").show();
            }
            if(json.skyStore.buyAndKeep.length > 0) {
                for (i = 0; i < json.skyStore.buyAndKeep.length; i++) {
                    var buyAndKeep = json.skyStore.buyAndKeep[i]
                    $("#buy-and-keep-table").append("<tr><td class=\"table-left-col\">" + buyAndKeep.title + "</td><td class=\"table-right-col\">" + formatCurrency(buyAndKeep.cost) + "</td></tr>)");
                }
                $("#buy-and-keep-section").show();
            }
            $("#sky-store-section").fadeIn();
        }
    });
}

$(function() {
  // When a user clicks the packages toggle.
  $("#package-toggle").click(function(e) {
    slideSection("#package-toggle",".package");
    e.preventDefault();
  });
  $("#call-charges-toggle").click(function(e) {
    slideSection("#call-charges-toggle",".call-charges");
    e.preventDefault();
  });
  $("#sky-store-toggle").click(function(e) {
    slideSection("#sky-store-toggle",".sky-store");
    e.preventDefault();
  });

  getBill("billjson");

});


