function timestampScript(url) {
    document.write("<script language='javascript' src='" + getTimestampUrl(url) + "'></script>");
}

function timestampCss(url) {
    document.write("<link rel='stylesheet' href='" + getTimestampUrl(url)+ "'/>");
}

function getTimestampUrl(url) {
    if (url.indexOf("?") > -1) {
        url = url + "&_t=" + Date.now()
    } else {
        url = url + "?_t=" + Date.now()
    }
    return url;
}