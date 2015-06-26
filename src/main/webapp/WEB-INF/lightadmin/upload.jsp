<h5>Upload a file to the Compendium </h5>

<form action="/data_extractor" method="post" enctype="multipart/form-data">
    <div class="upload">
        <input type="file" name="file"/>
    </div>
    <input type="submit" value="Send"/>
</form>

<style type="text/css">
    div.upload {
        width: 143px;
        height: 52px;
        background: url(https://lh6.googleusercontent.com/-dqTIJRTqEAQ/UJaofTQm3hI/AAAAAAAABHo/w7ruR1SOIsA/s157/upload.png);
        overflow: hidden;
    }

    div.upload input {
        display: block !important;
        width: 143px !important;
        height: 52px !important;
        opacity: 0 !important;
        overflow: hidden !important;
    }
</style>
