function required(event)
{
    var empt = document.form1.client_password.value;
    if (empt === "")
    {
        event.preventDefault();
        alert("Please input a Password");
        return false;
    }
    else
    {
        alert('Success!');
        return true;
    }
}
function required2(event)
{
    var empt = document.form1.client_password.value;
    if (empt === "")
    {
        event.preventDefault();
        alert("Please input a Password");
        return false;
    }
    else
    {
        alert('Success!');
        return true;
    }
}