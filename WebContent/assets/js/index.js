$(function(){
	// jQuery methods go here...
	var cop = 0;
	var browserId = 0;
	navButtonsGen();

    function printLogin(){
    	// prints a series of forms and inputs
    	// prints login and register buttons
    	var content = '<div class="col">\
				            <h3>Existing User</h3>\
				            <form>\
    							<div class="form-group"><select id="log_type" class="form-control" name="cop"><option value="1">Customer</option><option value="2">Partner</option></select></div>\
				                <div class="form-group"><label>Username:&nbsp;</label><input class="form-control" type="text" id="log_user"></div>\
				                <div class="form-group"><label>Password:&nbsp;</label><input class="form-control" type="text" id="log_pass"></div>\
				            </form><button class="btn btn-primary" id="loginButton" type="button">Login</button></div>\
				        <div class="col text-left">\
				            <h3>New User</h3>\
				            <p style="margin-bottom: 0px;">Partners can post trucks</p>\
				            <p style="margin-bottom: 10px;">Customers can rent posted trucks</p>\
				            <form>\
				                <div class="form-group"><select id="reg_type" class="form-control" name="cop"><option value="1">Customer</option><option value="2">Partner</option></select></div>\
				                <div class="form-group"><label>First name:&nbsp;</label><input class="form-control" type="text" id="reg_fname"></div>\
				                <div class="form-group"><label>Last name:&nbsp;</label><input class="form-control" type="text" id="reg_lname"></div>\
				                <div class="form-group"><label>Username:&nbsp;</label><input class="form-control" type="text" id="reg_uname"></div>\
				                <div class="form-group"><label>Password:&nbsp;</label><input class="form-control" type="text" id="reg_pass"></div>\
				            </form><button class="btn btn-primary" id="registerButton" type="button">Sign Up</button></div>\
				    	</div>';
    	$('#pageContent').html(content);
    	
    	$('#loginButton').click(function(){
    		var type =  $('#log_type').val();
    		if(type == 1){
    			url='http://localhost:8081/CustomerService/customer/login';
    		}else if(type == 2){
    			url='http://localhost:8081/PartnerService/partner/login';
    		}
    		var data= {
    				"user": $('#log_user').val(),
    				"pass":$('#log_pass').val()
    				}
    		
    		LoginRequest(data, url, type);
	    });

	    $('#registerButton').click(function(){
    		var type =  $('#reg_type').val();
    		var data;
    		var url;
    		if(type == 1){
    			url='http://localhost:8081/CustomerService/customer';
    		}else if(type == 2){
    			url='http://localhost:8081/PartnerService/partner';
    		}
    		var data= {
    				"firstName": $('#reg_fname').val(),
    				"lastName": $('#reg_lname').val(),
    				"user": $('#reg_uname').val(),
    				"pass":$('#reg_pass').val()
    				}
	    	registerUser(data, url, type);
	    });
    }

    function LoginRequest(d, url, type){
    	//get values from forms
		//GET user info from server
		// if return 422 then print error logging in, else (need to implement the check in the backend)
		// clear page and go to home page or user page? with out the logsigButton, replaced with the accsetbutton
    	//console.log("Get User " +  type);
    	$.ajax({
    		headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                "Access-Control-Allow-Origin": "http://localhost:8081"
            },
    		type: "POST",
    		url : url,
    		data: JSON.stringify(d),
    		'success': function(res){
    			console.log(res)
    			browserId = res.id
    			cop = type
    			printUserInfo(res)
    		}
    	});
    }

	function registerUser(d, url, type){
		//get values from forms
		//POST user info from server
		// clear page and go to home page or user page? with out the logsigButton, replaced with the accsetbutton
		console.log("register user");
		$.ajax({
    		headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                "Access-Control-Allow-Origin": "http://localhost:8081"
            },
    		type: "POST",
    		url : url,
    		data: JSON.stringify(d),
    		'success': function(res){
    			console.log(res)
    			browserId = res.id
    			cop = type
    			printUserInfo(res)
    		}
    	});
	}

	function getInventoryList(link){
		//get params and get inventory list
		//id will determine if we get site list, id=0, or partner list id= some partners id
		//generate a get request from that
		if(link==undefined){
			$.getJSON('http://localhost:8081/VehicleService/vehicle?id='+ browserId +'&cop=' + cop ,function (res){
				$.each(res, function(i, d){
					console.log(d);
					inv = d;
			})
			printInventoryList(inv, 0);}
		);} else{
			$.getJSON(link,function (res){
				$.each(res, function(i, d){
					console.log(d);
					inv = d;
			})
			printInventoryList(inv, 2);}
		);}
		
	}
	
	function navButtonsGen(){
		if(browserId == 0 || cop == 0){
			$("#navButtons").html('<a id="siteInventoryButton" class="btn btn-primary" role="button" style="margin-top: 10px;margin-left: 10px;">Site Inventory</a>');
			$("#navButtons").append('<a id="logsigButton" class="btn btn-primary" role="button" style="margin-top: 10px;margin-left: 10px;">Login / Signup</a>');
			
			$('#logsigButton').click(function(){
		    	console.log("clicking login button")
		    	printLogin();
		    })
		    
		} else {
			$("#navButtons").html('<a id="siteInventoryButton" class="btn btn-primary" role="button" style="margin-top: 10px;margin-left: 10px;">Site Inventory</a>');
			$("#navButtons").append('<a id="accSetButton" class="btn btn-primary" role="button" style="margin-top: 10px;margin-left: 10px;">Account Settings</a>');
			
			$('#accSetButton').click(function(){
		    	console.log("clicking account setings")
		    	if(cop == 1){
		    		getUserInfo('http://localhost:8081/CustomerService/customer/'+browserId+'?id='+ browserId +'&cop=' + cop );
		    	}else if(cop == 2){
		    		getUserInfo('http://localhost:8081/PartnerService/partner/'+browserId+'?id='+ browserId +'&cop=' + cop );
		    	}
		    })
		}
		
		//run print inv
	    $('#siteInventoryButton').click(function(){
	    	getInventoryList();
	    })
	}
	
	function printInventoryList(inventory, sop){
		//passing in a json of inventory, need to print to html and add view buttons dynamically
		function createVehButtonLink(num){
			return function(){
				console.log('View Veh ' + inventory[num].vehicleId);
				getVehicleInfo(inventory[num].link[0].url);
	        }
		}
		navButtonsGen();
		var content = '<div id="inv" class="col">\
						<h2>Inventory</h2>\
						<div class="table-responsive">\
							<table class="table">\
								<thead>\
									<tr id="Table-Heading">\
										<th>ID</th>\
										<th>Make</th>\
										<th>Model</th>\
										<th>Year</th>\
										<th>Price</th>\
										<th>Availability</th>\
										<th>View</th>\
									</tr>\
								</thead>\
								<tbody>';

		for (var i = 0; i < inventory.length; i++) {
			content += '<tr id="vehicle">\
                                <td>' +inventory[i].vehicleId+ '</td>\
                                <td>' +inventory[i].make+ '</td>\
                                <td>' +inventory[i].model+ '</td>\
                                <td>' +inventory[i].year+ '</td>\
                                <td>' +inventory[i].price+ '</td>\
                                <td>' +inventory[i].availability+ '</td>\
            					<td><button id=viewVeh_'+inventory[i].vehicleId+' class="btn btn-primary" type="button">'+inventory[i].link[0].action+'</button></td>\
            					</tr>';
			
		}

		if(sop == 0){
			$('#pageContent').html(content);
		}else if(sop == 2){
			$('#userLists').append(content);
		}
		
		for (var i = 0; i < inventory.length; i++) {
			$('#viewVeh_'+inventory[i].vehicleId).click(createVehButtonLink(i));
		}
		
	}

	function getOrderInfo(link){
		// generate GET order with params and orderId
		$.getJSON(link, function (res){
			printOrderInfo(res);
		})
	}

	function printOrderInfo(order){
		//passing in a json Order, need to print to html
		console.log(order);
		var content ='<div id="order" class="col">\
				            <div class="row">\
				                <div class="col">\
				                    <h4 class="text-right">Order ID</h4>\
				                </div>\
				                <div class="col">'+order.orderId+'</div>\
				                <div class="col"></div>\
				            </div>\
				            <div class="row">\
				                <div class="col"><h4 class="text-right">Vehicle</h4></div>\
				                <div class="col" id="make">'+ order.vehicleId +'</div>\
				                <div class="col" id="vehicleSection"></div>\
				            </div>\
				            <div class="row">\
				                <div class="col"><h4 class="text-right">Partner</h4></div>\
				                <div class="col" id="model">'+ order.partnerId +'</div>\
				                <div class="col" id= "viewPartner"></div>\
				            </div>\
				            <div class="row">\
				                <div class="col"><h4 class="text-right">Customer</h4></div>\
				                <div class="col" id="year">'+ order.customerId +'</div>\
				                <div class="col" id="viewRenter"></div>\
				            </div>\
				            <div class="row">\
				                <div class="col"><h4 class="text-right">Order Status</h4></div>\
				                <div class="col" id="price"> in progress...</div>\
				                <div class="col"></div>\
				            </div>\
				        </div>';
		
		$('#pageContent').html(content);
		
		for(var i=0; i < order.link.length; i++){
			console.log(order.link[i].action)
			var button;
			switch(order.link[i].action){
				case "View Vehicle Owner":
					button = '<div class="col"><button class="btn btn-primary" id="getpartbut" type="button" style="padding: 4px;">'+order.link[i].action+'</button></div>';
					var ownerLink = order.link[i].url;
					$('#viewPartner').append(button);
					break;
				case "View Vehicle":
					button = '<div class="col"><button class="btn btn-primary" id="getVeh" type="button" style="padding: 4px;">'+order.link[i].action+'</button></div>';
					var viewVeh = order.link[i].url;
					$('#vehicleSection').append(button);
					break;
				case "View Renter":
					button = '<div class="col"><button class="btn btn-primary" id="getcustbut" type="button" style="padding: 4px;">'+order.link[i].action+'</button></div>';
					var custLink = order.link[i].url;
					$('#viewRenter').append(button);
					break;
			}
		}
		
		$('#getpartbut').click(function(){
			getUserInfo(ownerLink);
		});
		
		$('#getVeh').click(function(){
			getVehicleInfo(viewVeh);
		});
		
		$('#getcustbut').click(function(){
			getUserInfo(custLink);
		});
		navButtonsGen();
	}

	
	function getVehicleInfo(link){
		// generate GET vehicle with link from inventory
		$.getJSON(link, function (res){
			printVehicleInfo(res);
		})
	}
	
	function printVehicleInfo(vehicle){
		//passing in a json Vehicle, need to print to html
		console.log(vehicle);
		var content ='<div id="vehicle" class="col">\
				            <div class="row">\
				                <div class="col">\
				                    <h4 class="text-right">Vehicle ID</h4>\
				                </div>\
				                <div class="col" id="vehicleId">'+vehicle.vehicleId+'</div>\
				                <div class="col" id="availability">\
				                    <h5>'+ vehicle.availability +'</h5></div>\
				                </div>\
				            <div class="row">\
				                <div class="col"><h4 class="text-right">Make</h4></div>\
				                <div class="col" id="make"><input class="form-control" type="text" id="vehMake" value = "'+ vehicle.make +'" readonly></div>\
				                <div class="col" id="rentSection"></div>\
				            </div>\
				            <div class="row">\
				                <div class="col"><h4 class="text-right">Model</h4></div>\
				                <div class="col" id="model"><input class="form-control" type="text" id="vehModel" value = "'+ vehicle.model +'" readonly></div>\
				                <div class="col"></div>\
				            </div>\
				            <div class="row">\
				                <div class="col"><h4 class="text-right">Year</h4></div>\
				                <div class="col" id="year"><input class="form-control" type="text" id="vehYear" value = "'+ vehicle.year +'" readonly></div>\
				                <div class="col" id="editSection"></div>\
				            </div>\
				            <div class="row">\
				                <div class="col"><h4 class="text-right">Price</h4></div>\
				                <div class="col" id="price"><input class="form-control" type="text" id="vehPrice" value = "'+ vehicle.price +'" readonly></div>\
				                <div class="col"></div>\
				            </div>\
				            <div class="row">\
				                <div class="col"><h4 class="text-right">Partner</h4></div>\
				                <div class="col" id="partner">' + vehicle.partnerId +'</div>\
				                <div class="col" id="viewPartner"></div>\
				            </div>\
				        </div>';
		
		$('#pageContent').html(content);
		
		for(var i=0; i < vehicle.link.length; i++){
			console.log(vehicle.link[i].action)
			var button;
			switch(vehicle.link[i].action){
				case "View Vehicle Owner":
					//Print User
					button = '<div class="col"><button class="btn btn-primary" id="getpartbut" type="button" style="padding: 4px;">'+vehicle.link[i].action+'</button></div>';
					var ownerLink = vehicle.link[i].url;
					$('#viewPartner').append(button);
					break;
				case "Rent":
					button = '<div class="col"><button class="btn btn-primary" id="rent" type="button" style="padding: 4px;">'+vehicle.link[i].action+'</button></div>';
					var rentLink = vehicle.link[i].url;
					//create and order
					$('#rentSection').append(button);
					break;
				case "Edit Vehicle":
					button = '<div class="col"><button class="btn btn-primary" id="editButton" type="button" style="padding: 4px;">'+vehicle.link[i].action+'</button></div>';
					//make edit Vehicle button and delete
					var editVehLink = vehicle.link[i].url;
					$('#editSection').append(button);
					break;
				case "Delete Vehicle":
					var deleteVehLink = vehicle.link[i].url;
					break;
			}
		}
		
		$('#getpartbut').click(function(){
			getUserInfo(ownerLink);
		});
		
		$('#rent').click(function(){
			var data ={
					"customerId" : browserId,
					"vehicleId" : vehicle.vehicleId,
					"partnerId" : vehicle.partnerId
			}
			createOrder(rentLink, data);
		});
		
		$('#editButton').click(function(){
			// activate the inputs
			$('#vehMake').prop('readonly', false);
			$('#vehModel').prop('readonly', false);
			$('#vehYear').prop('readonly', false);
			$('#vehPrice').prop('readonly', false);
			// generate edit buttons
			$('#editSection').html('<a id=saveEditVeh class="btn btn-primary" role="button" id="edit" style="margin-top: 10px;margin-left: 10px;">Save Changes</a>');
			$('#editSection').append('<a id=cancelEditVeh class="btn btn-primary" role="button" id="edit" style="margin-top: 10px;margin-left: 10px;">Cancel Edit</a>');
			$('#editSection').append('<a id=deleteVeh class="btn btn-primary" role="button" id="edit" style="margin-top: 10px;margin-left: 10px;">Delete Vehicle</a>');
			
			$('#saveEditVeh').click(function(){
				// this will send a put request to the specific user using their link
				// which will return an updated user representation which we then print
				var data ={
						"make" : $('#vehMake').val(),
						"model" : $('#vehModel').val(),
						"year" : $('#vehYear').val(),
						"price" : $('#vehPrice').val(),
				}
				editVehicle(editVehLink, data)
			})
			$('#cancelEditVeh').click(function(){
				printVehicleInfo(vehicle);
			})
			$('#deleteVeh').click(function(){
				// this will send a delete request to the specific user using their link
				$.ajax({
		    		headers: {
		                'Accept': 'application/json',
		                'Content-Type': 'application/json',
		                "Access-Control-Allow-Origin": "http://localhost:8081"
		            },
		    		type: "DELETE",
		    		url : deleteVehLink,
		    		'success': function(){
		    			console.log("deleted vahicle")
		    		}
		    	});
				getInventoryList();
			})
		});
		navButtonsGen();
	}
	
	function createOrder(url, d){
		$.ajax({
    		headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                "Access-Control-Allow-Origin": "http://localhost:8081"
            },
    		type: "POST",
    		url : url,
    		data: JSON.stringify(d),
    		'success': function(res){
    			console.log(res)
    			printOrderInfo(res)
    		}
    	});
	}
	
	function editVehicle(url, d){
		//printVehicleInfo(vehicle);
		console.log(d)
		$.ajax({
    		headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                "Access-Control-Allow-Origin": "http://localhost:8081"
            },
    		type: "PUT",
    		url : url,
    		data: JSON.stringify(d),
    		'success': function(res){
    			console.log(res)
    			printVehicleInfo(res)
    		}
    	});
	}
	
	function addVehicle(d, url){
		// generate GET vehicle with params and input values for vehicle
		$.ajax({
    		headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                "Access-Control-Allow-Origin": "http://localhost:8081"
            },
    		type: "POST",
    		url : url,
    		data: JSON.stringify(d),
    		'success': function(res){
    			console.log(res)
    			printVehicleInfo(res)
    		}
    	});
	}

	function getOrderList(link){
		// should only print the owners orders list, so all we need are id and cop params from uri
		// generate xml get request
		$.getJSON(link, function (res){
			console.log(res.reps);
			printOrdersList(res.reps);
		})
	}

	function printOrdersList(orders){
		//passing in a json of orders, need to print to html and add view buttons dynamically
		//will be printed in #userlists
		function createOrderButtonLink(num){
			return function(){
				console.log('View Order ' + orders[num].vehicleId);
				getOrderInfo(orders[num].link[0].url);
	        }
		}
		var content = '<div class="col" id="orders">\
			            <h2>Orders</h2>\
			            <div class="table-responsive">\
			                <table class="table">\
			                    <thead>\
			                        <tr id="Table-Heading-1">\
			                            <th>Order ID</th>\
			                            <th>Vehicle ID</th>\
			                            <th>Customer ID</th>\
			                            <th>Partner ID</th>\
			                            <th>View</th>\
			                        </tr>\
			                    </thead>\
			                    <tbody>'
			
			for (var i = 0; i < orders.length; i++) {
				content += ' <tr id="order">\
			                    <td>'+orders[i].orderId+'</td>\
			                    <td>'+orders[i].vehicleId+'</td>\
			                    <td>'+orders[i].customerId+'</td>\
			                    <td>'+orders[i].orderId+'</td>\
			                    <td><button id=viewOrd_'+orders[i].orderId+' class="btn btn-primary" type="button">'+orders[i].link[0].action+'</button></td>\
			                </tr>'
				
			}
		
		$('#userLists').append(content);
		
		for (var i = 0; i < orders.length; i++) {
			console.log("make button")
			$('#viewOrd_'+ orders[i].orderId).click(createOrderButtonLink(i));
		}
	}

	function getUserInfo(userLink){
		console.log(userLink);
		// generate GET user with link
		$.getJSON(userLink, function (res){
			printUserInfo(res);
		});
	}
	
	function editUser(url, d){
		$.ajax({
    		headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                "Access-Control-Allow-Origin": "http://localhost:8081"
            },
    		type: "PUT",
    		url : url,
    		data: JSON.stringify(d),
    		'success': function(res){
    			console.log(res)
    			printUserInfo(res)
    		}
    	});
	}
	
	function printUserInfo(user){
		//passing in a json User, need to print to html
		console.log(user);
		navButtonsGen();
		var content ='<div class="col">\
			            <div class="row">\
					        <div class="col">\
					            <div class="row">\
					                <div class="col-md-6">\
					                    <h4 class="text-right">User ID:</h4>\
					                </div>\
					                <div class="col" id="userID"><h5>'+user.id+'</h5></div>\
					            </div>\
					            <form>\
					                <div class="form-group">\
										<label>First Name</label>\
										<input class="form-control" value="'+user.firstName+'" type="text" id="fname" name="fname" readonly="">\
									</div>\
									<div class="form-group">\
										<label>Last Name</label>\
										<input class="form-control" value="'+user.lastName+'" type="text" id="lname" name="lname" readonly="">\
									</div>\
					            </form>\
					        </div>\
					        <div class="col text-left" id="UserOptions">\
					        </div>\
					    </div>\
					    <div class="row" id="userLists"></div>\
					</div>';

		$('#pageContent').html(content);
		if(user.link != undefined){
			for(var i=0; i < user.link.length; i++){
				
				console.log(user.link[i].action)
				
				switch(user.link[i].action){
				////Partner links
					case "View Partner's Inventory":
						//Print Inventory AUTOMATIC
						getInventoryList(user.link[i].url, 2)
						break;
					case "Get Orders":
						//Print orders AUTOMATIC
						console.log(user.link[i].url)
						getOrderList(user.link[i].url)
						break;
					case "Edit Partner":
						//make edit partner button
						var button = '<div class="col"><button class="btn btn-primary" id="editUser" type="button" style="padding: 4px;">'+user.link[i].action+'</button></div><br>';
						editLink = user.link[i].url;
						$('#UserOptions').append(button);
						break;
					case "Delete Partner":
						deleteLink = user.link[i].url;
						break;
					case "Add Vehicle":
						var button = '<div class="col"><button class="btn btn-primary" id="showVehFields" type="button" style="padding: 4px;">'+user.link[i].action+'</button></div><br>';
						addVehLink = user.link[i].url;
						$('#UserOptions').append(button);
						break;
				////Customer Links
					case "Edit Customer":
						var button = '<div class="col"><button class="btn btn-primary" id="editUser" type="button" style="padding: 4px;">'+user.link[i].action+'</button></div><br>';
						editLink = user.link[i].url;
						console.log(button)
						$('#UserOptions').append(button);
						break;
					case "Delete Customer":
						deleteLink = user.link[i].url;
						break;
				}
			}
			
		}
		
		$('#showVehFields').click(function(){
			var vehFields = '<form>\
					            <div class="form-group">\
									<label>Make</label>\
									<input class="form-control" type="text" id="make">\
								</div>\
								<div class="form-group">\
									<label>Model</label>\
									<input class="form-control" type="text" id="model">\
								</div>\
								<div class="form-group">\
									<label>Year</label>\
									<input class="form-control" type="text" id="year">\
								</div>\
								<div class="form-group">\
									<label>Price</label>\
									<input class="form-control" type="text" id="price">\
								</div>\
					        </form>';
			$('#UserOptions').html(vehFields);
			$('#UserOptions').append('<a id=addVehicle class="btn btn-primary" role="button" id="edit" style="margin-top: 10px;margin-left: 10px;">Add Vehicle</a>');
			$('#UserOptions').append('<a id=cancelAdd class="btn btn-primary" role="button" id="edit" style="margin-top: 10px;margin-left: 10px;">Cancel</a>');
			
			$('#addVehicle').click(function(){
	    		var data ={
	    				"make": $('#make').val(),
	    				"model": $('#model').val(),
	    				"year": $('#year').val(),
	    				"price": $('#price').val()
	    				}
	    		var url= addVehLink;
				addVehicle(data, url);
			})
			
			$('#cancelAdd').click(function(){
				printUserInfo(user);
			})
		})
		
		$('#editUser').click(function(){ // Give edit user options
			// activate the inputs
			$('#fname').prop('readonly', false);
			$('#lname').prop('readonly', false);
			// generate edit buttons
			$('#UserOptions').html('<a id=saveEditUser class="btn btn-primary" role="button" id="edit" style="margin-top: 10px;margin-left: 10px;">Save Changes</a>');
			$('#UserOptions').append('<a id=cancelEditUser class="btn btn-primary" role="button" id="edit" style="margin-top: 10px;margin-left: 10px;">Cancel Edit</a>');
			$('#UserOptions').append('<a id=deleteUser class="btn btn-primary" role="button" id="edit" style="margin-top: 10px;margin-left: 10px;">Delete User</a>');
			
			$('#saveEditUser').click(function(){
				// this will send a put request to the specific user using their link
				// which will return an updated user representation which we then print
				var data ={
	    				"firstName": $('#fname').val(),
	    				"lastName": $('#lname').val(),
	    				}
	    		var url = editLink;
				editUser(url, data);
			})
			$('#cancelEditUser').click(function(){
				printUserInfo(user);
			})
			$('#deleteUser').click(function(){
				// this will send a delete request to the specific user using their link
				$.ajax({
		    		headers: {
		                'Accept': 'application/json',
		                'Content-Type': 'application/json',
		                "Access-Control-Allow-Origin": "http://localhost:8081"
		            },
		    		type: "DELETE",
		    		url : deleteLink
		    	});
    			browserId = 0;
    			cop = 0;
    			getInventoryList();
			})
		})
	}

});