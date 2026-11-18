<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Checkout - Highyat</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/style.css">
    <style>
        .checkout-box {
            background-color: #fff;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 5px 15px rgba(0,0,0,0.1);
            max-width: 600px;
            margin: 40px auto;
        }

        .checkout-box h2 {
            margin-top: 0;
            margin-bottom: 10px;
            font-size: 24px;
            font-weight: bold;
        }

        .checkout-box p {
            color: #444;
            margin-bottom: 20px;
        }

        .checkout-box .btn-next {
            background-color: #111827;
            color: white;
            padding: 10px 20px;
            font-weight: bold;
            text-decoration: none;
            border-radius: 6px;
            display: inline-block;
        }

        .checkout-box .btn-next:hover {
            background-color: #1f2937;
        }
    </style>
</head>
<body>

<%@ include file="../includes/header.jsp" %>

<main>
    <div class="checkout-box">
        <h2>Checkout Summary</h2>
        <p>Here you can review your order before proceeding.</p>

        <a href="<%= request.getContextPath() %>/jsp/customer/payment.jsp" class="btn-next">Continue to Payment</a>
    </div>
</main>

<%@ include file="../includes/footer.jsp" %>

</body>
</html>
