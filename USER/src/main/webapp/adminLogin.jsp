<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>GadgetBadget</title>

    <link rel="stylesheet" href="build/css/adminStyle.css">
</head>
<body class="nav-md" style="cursor: pointer">
<!-- Sign up form -->
<h2> GadgetBadget Admin Portal</h2>
<div class="container" id="container">
    <div class="form-container sign-in-container">
        <form action="http://localhost:8060/USER/rest1/Login" method="post">
            <h1>Sign in</h1>
            <span>enter your credentials below</span>
            <input type="username" placeholder="Username" name="userName" />
            <input type="password" placeholder="Password" name="password"/>
            <button type="submit">Sign In</button>
        </form>
    </div>
    <div class="overlay-container">
        <div class="overlay">
            <div class="overlay-panel overlay-right">
                <h1>Hello Admin!</h1>
                <p>Welcome to GadgetBadget Administration</p>
            </div>
        </div>
    </div>
</div>
<script src="build/js/main.js"></script>
</body>
</html>