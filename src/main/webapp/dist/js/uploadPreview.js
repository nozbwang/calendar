document.write("<script language=javascript src='plugins/lrz/lrz.bundle.js'></script>");

function toFixed2 (num) {
    return parseFloat(+num.toFixed(2));
}

$("#exampleInputFileConfirm").attr("disabled","disabled");

document.querySelector('#exampleInputFilePreview').addEventListener('change', function () {

    $("#exampleInputFileConfirm").removeAttr("disabled");
    var that = this;

    lrz(that.files[0], {width: 2048})
        .then(function (rst) {
            var img = new Image();
            var div = document.createElement('div');
            div.id = "imagePreview";
            p = document.createElement('p');
            sourceSize = toFixed2(that.files[0].size / 1024);
            resultSize = toFixed2(rst.fileLen / 1024);
            scale = parseInt(100 - (resultSize / sourceSize * 100));

            p.style.fontSize = 13 + 'px';
            p.style.textAlign = "left";
            p.innerHTML  = 'From：<span class="text-danger">' + sourceSize + 'KB' +
                '</span> <br />' +
                'To：<span class="text-success">' + resultSize + 'KB (省' + scale + '%)' +
                '</span> ';

            div.className = 'col-sm-10';
            div.appendChild(img);
            div.appendChild(p);

            img.onload = function () {
                $("#imagePreview").remove();
                document.querySelector('#upload-container').appendChild(div);
            };

            img.src = rst.base64;
            return rst;
        })
});

document.querySelector('#exampleInputFileConfirm').addEventListener('click', function () {
    var that = document.querySelector('#exampleInputFilePreview');

   lrz(that.files[0], {width: 2048})
        .then(function (rst) {
            $("#uploadProgressDiv").show();
            $("#uploadProgress").css("width","10%").text("");
            rst.formData.append('fileLen', rst.fileLen);
            rst.formData.append('fileName', rst.origin.name);
            rst.formData.append('belongType', $("#belongType").val());
            $.ajax({
                url: 'attach/upload',
                data: rst.formData,
                processData: false,
                contentType: false,
                type: 'POST',
                dataType: "json",
                success: function (data) {
                    if(data.success){
                        $("#attachId").attr("value", data.content.id);
                        $("#uploadProgress").css("width","100%").text("上传成功");
                        $("#uploadProgressDiv").hide(1000);
                        $("#exampleInputFileConfirm").attr("disabled","disabled");
                    }
                }
            });
        })
});