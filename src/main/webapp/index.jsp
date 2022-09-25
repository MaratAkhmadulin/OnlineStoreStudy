<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Online Store</title>
</head>
<script type="text/javascript">
window.onload = formSubmit;

function formSubmit() {

document.forms[0].submit();
}
</script>
<body>
<form method="get" action="main">
<input type="hidden" name="stage" value="main">

</form>
</body>
</html>