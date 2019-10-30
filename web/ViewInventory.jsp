<%@page import="com.inventory.util.InventoryConstants"%>
<%@page import="com.inventory.model.InventoryItem"%>
<%@page import="java.util.*"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Inventory</title>

	<link rel="stylesheet" type="text/css" href="./css/Main.css">
	<link rel="stylesheet" type="text/css" href="./css/components.css">
    <script src="https://kit.fontawesome.com/d2591e384b.js" crossorigin="anonymous"></script>
</head>

<body>
    <!--Header -->
    <div class="header">
        <div class="header-logo">
            <h1>Shopify</h1>
        </div>
        <div class="header-content">
            <div class="header-search-bar">
                <%
                	InventoryItem item = (InventoryItem) request.getAttribute(InventoryConstants.INVENTORY_ITEM);
                	if (item != null)
                	{
                		out.print("<input type=\"text\" name=\"header-search\" placeholder=\"Search Inventory...\" value=\" " + item.getName() + " \">");
                	}
                	else 
                	{
                		out.print("<input type=\"text\" name=\"header-search\" placeholder=\"Search Inventory...\">");	
                	}
                %>
            </div>
        </div>
    </div>

    <div class="main-body">
        <!--Sidebar-->
        <nav>
            <div class="sidenav">
                <ul>
                    <li>
                        <a href="./InventoryDashboard.html">Dashboard</a>
                    </li>

                    <li>
                        <a href="./NewInventory.html">New Inventory</a>
                    </li>

                    <li>
                        <a href="./ViewInventory.html">View Inventory</a>
                    </li>
                </ul>
            </div>
        </nav>

        <!--Main Content-->
        <div class="main-content">
            <div class="main-content-header">
                <h1>Inventory</h1>
            </div>

            <div class="main-content-body">

				<%
					if (item != null) 
					{
						out.print("<div class=\"card-wrapper card-sm\">");
						out.print("<h3>Nivea Body Lotion</h3>");
						out.print("<div class=\"card-body\">");
						out.print("<ul>");
						out.print("<li><span class=\"card-label\">Brand Name:</span>" + item.getName() + "</li>");
						out.print("<li><span class=\"card-label\">Quantity:</span>" + item.getQuantity() + "</li>");
						out.print("<li><span class=\"card-label\">Category:</span>" + item.getCategory().getName() + "</li>");
						out.print("</ul>");
						out.print("</div>");
						out.print("</div>");
					}
					else 
					{
						
					}
				%> 
            </div>
        </div>
    </div>

    <div class="footer">
        All rights reserved &copy; 2019
    </div>
</body>

</html>