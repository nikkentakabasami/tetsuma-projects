<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
</head>
<body>

<#assign x = "    moo  \n\n   ">

(<#compress>
  1 2  3   4    5
  ${x}
  test only

  I said, test only

</#compress>)  

   
</body>
</html>

