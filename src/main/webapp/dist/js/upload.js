document.write("<script language=javascript src='plugins/lrz/lrz.bundle.js'></script>");

document.querySelector('#exampleInputFile').addEventListener('change', function () {
    lrz(this.files[0], {width: 2048})
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
                    }
                }
            });
        })
});