document.write("<script language=javascript src='plugins/jQuery/lazyload.js'></script>");

$(function() {
    $(".lazy").lazyload({effect: "fadeIn"});
});
