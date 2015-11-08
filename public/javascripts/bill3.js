function slideSection(link, section) {
    $(section).slideToggle();
    var state = $(link).text();
    if(state=="Show") {
      $(link).text("Hide");
    } else {
      $(link).text("Show");
    }
  }

$(function() {
  // When a user clicks the packages toggle.
  $("#package-toggle").click(function() {
    slideSection("#package-toggle",".package");
  });
  $("#call-charges-toggle").click(function() {
    slideSection("#call-charges-toggle",".call-charges");
  });
  $("#sky-store-toggle").click(function() {
    slideSection("#sky-store-toggle",".sky-store");
  });


    $.get( "billjson", function(json) {
    console.log(json)
        $("#statement-date").html(json.statement.generated);
        $("#statement-from").html(json.statement.period.from);
        $("#statement-to").html(json.statement.period.to);
        $("#package-total").html(json.package.total);
        $("#call-charges-total").html(json.callCharges.total);
        $("#sky-store-total").html(json.skyStore.total);
        $("#statement-due").html(json.statement.due);
        $("#bill-total").html(json.total);

        for (i = 0; i < json.package.subscriptions.length; i++) {
            var sub = json.package.subscriptions[i]
            $("#package-table").append("<tr><td class=\"table-left-col\">" + sub.type + "</td><td>" + sub.name + "</td><td class=\"cost\">&pound;" + sub.cost + "</td></tr>)");
        }

        for (i = 0; i < json.callCharges.calls.length; i++) {
            var call = json.callCharges.calls[i]
            $("#call-charges-table").append("<tr><td class=\"table-left-col\">" + call.called + "</td><td>" + call.duration + "</td><td class=\"cost\">&pound;" + call.cost + "</td></tr>)");
        }

        for (i = 0; i < json.skyStore.rentals.length; i++) {
            var rental = json.skyStore.rentals[i]
            $("#rentals-table").append("<tr><td class=\"table-left-col\">" + rental.title + "</td><td class=\"cost\">&pound;" + rental.cost + "</td></tr>)");
        }

        for (i = 0; i < json.skyStore.buyAndKeep.length; i++) {
            var buyAndKeep = json.skyStore.buyAndKeep[i]
            $("#buy-and-keep-table").append("<tr><td class=\"table-left-col\">" + buyAndKeep.title + "</td><td class=\"cost\">&pound;" + buyAndKeep.cost + "</td></tr>)");
        }


    });
});


