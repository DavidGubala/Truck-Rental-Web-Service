# Shipper (Comp 388-001 Project)

This is a complete web-service project made for Comp 388-001: The Web Services Programming course at Loyola University Chicago
This project is supposed to serve as a Truck Rental company. Similar to U-haul, except that the trucks for rent are
not owned by Shipper, but rather owned, posted and managed by Partners of Shippper.

This project was originally located at [bitbucket](https://bitbucket.org/ethomas14/comp333_433_truck_rental/src/master/) and imported to this repository upon completion.

## Technology

	-PostgreSQL
	-Java
	-Apache CXF 2.7.9
	-Apache Tomcat 9.0.40
	-Javascript (with JQuery, parses HATEAOS links and generates necessary html)

## Usage

	Viewer
		- View site inventory
			- View Vehicle
				- View Owner
					- View Partner's Inventory
		- Signup/Login

	Customer
		- View inventory
			- View Vehicle
				- Veiw Owner
					- View Partner's Inventory
				- Rent
		- View Account details/settings
			- View Orders
			- Edit Customer
				-Delete Customer

	Partner
		- View Vehicle
			- Veiw Owner
					- View Partner's Inventory
			- Rent
		- View Account details/settings
			- View Orders
			- Edit Partner
				-Delete Partner
			- View nventory


Query parameters ID and COP are set to 0 when visiting the site and later set by the frot end upon user Login/Registration.
The ID will be the user's ID as stored in the database, and COP is used to identify wether the user is a viewer(0), customer(1), or partner(2).
These query parameters in the URI are used in the backend to identify the user and validate certain functions. (e.g. Partner's have the ability to edit vehicles, )

## Models
### Databases

### Domains

### Workflows

## Known Issues
	- Vehicle Availability does nothing, defautls to Available
	- Order Status does nothing, default to Open
	- Invalid login throws CORS error from server
	- No logout function
	- User is logged out upon page refresh