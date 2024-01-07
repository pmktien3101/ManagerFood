<html>
    <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>About Us</title>
            <%
                String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                        + request.getContextPath();
            %>
            <link href="<%=url%>/css/aboutUs.css" rel="stylesheet">
        </head>

        <body>
            <div class="about-section" style="color: #000000">About <i class="fa fa-utensils"></i></div>
            <div class="content-section">
                Welcome to Slc!

                We specialize in providing fresh and high-quality food items, including a variety of fruits, vegetables, and
                packaged goods. Our mission is to bring you the best selection of farm-fresh produce and carefully curated
                packaged items.
                <br>
                Explore our range of fresh, organic, and locally sourced products. From vibrant fruits to nutritious vegetables,
                we have something for everyone.
                <br>
                For any inquiries or collaborations, feel free to contact us at: info@Slc@gmail.com
                <br>
                Follow us on Instagram: @SlcCompany
            </div>
            <div class="footer">

                <p>Email : slccompany@gmail.com</p>
                <p>Address: 15 Nguyen Cong Tru, District 1, HCMC</p>
                <h5>&copy; Copyright 2023. Slc.com</h5>

            </div>
        </body>

    </html>