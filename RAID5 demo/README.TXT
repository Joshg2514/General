INSTRUCTIONS:

- IN THE WORKING DIRECTORY IS A RAID5.java FILE, AND A COMPILED RAID5.class FILE. THEY ACCEPT 4 RUNTIME ARGUMENTS (NUMBER OF DRIVES, BYTES PER DRIVE, USER COMMAND (write, read, rebuild), AND FILE NAME) IN THE SPECIFIED ORDER.

- TO READ, YOU NEED THE DRIVES, disc1.txt, THROUGH discn.txt, AND ALSO A BLANK output.txt FILE IN THE WORKING DIRECTORY. THE PROGRAM WILL READ FROM THE DRIVES, AND OUTPUT FROM THE DATA IN THE output.txt FILE. 
	(EXAMPLE: java RAID5 2 2 read output.txt)

- TO WRITE, AN input.txt FILE IS REQUIRED. DURING RUNTIME, disc1.txt THROUGH discn.txt IS CREATED, CORRESPONDING TO THE n DRIVES. THE DATA FROM THE input.txt FILE WILL BE PARTITIONED INTO THE DRIVES. 
	(EXAMPLE: java RAID5 2 2 write input.txT)

- TO RECONSTRUCT DRIVES, YOU WILL NEED THE REMAINING DRIVES, A BLANK drivek.txt FILE, AND THE PARITY DATA FROM THE REMAINING DRIVES WILL ALL BE USED TO RECONSTRUCT THE MISSING DRIVE. 
	(EXAMPLE: java RAID5 4 4 rebuild disc2.txt)