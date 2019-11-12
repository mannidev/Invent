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
                        <a href="./newInventory.sh">View Inventory</a>
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

				<div class="cards">
					<%
					if (item != null) 
					{
						out.println("<div class=\"card-wrapper card-sm\">");
						out.println("<h3>" + item.getName() + "</h3>");
						out.println("<div class=\"card-body\">");
						out.println("<ul>");
						out.println("<li><span class=\"card-label\">Brand Name:</span>" + item.getName() + "</li>");
						out.println("<li><span class=\"card-label\">Quantity:</span>" + item.getQuantity() + "</li>");
						out.println("<li><span class=\"card-label\">Category:</span>" + item.getCategory().getName() + "</li>");
						out.println("</ul>");
						out.println("</div>");
						out.println("</div>");
					}
					else 
					{
						List<InventoryItem> inventory = (List<InventoryItem>) request.getAttribute(InventoryConstants.INVENTORY); 
						for (InventoryItem inventoryItem : inventory)
						{
							out.println("<div class=\"card-wrapper card-sm\">");
							out.println("<h3>" + inventoryItem.getName() + "</h3>");
							out.println("<div class=\"card-body\">");
							out.println("<ul>");
							out.println("<li><span class=\"card-label\">Brand Name:</span>" + inventoryItem.getName() + "</li>");
							out.println("<li><span class=\"card-label\">Quantity:</span>" + inventoryItem.getQuantity() + "</li>");
							out.println("<li><span class=\"card-label\">Category:</span>" + inventoryItem.getCategory().getName() + "</li>");
							out.println("</ul>");
							out.println("</div>");
							out.println("</div>");
						}
					}
				%> 
				</div>
				
            </div>
        </div>
    </div>

    <div class="footer">
        All rights reserved &copy; 2019
    </div>
</body>

</html>