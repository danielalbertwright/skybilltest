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
});


