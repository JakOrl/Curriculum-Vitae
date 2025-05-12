# Author: Jakub Orlowski
# Date starting: 05/12/24

import csv
import os

# Default file name
save_file = "tracker.csv"
# Creating file name by user
if not os.path.isfile(save_file):
    save_file = input("Enter the name of the tracker:")

# Checking if file exists but using a variable
save_file_exists = os.path.isfile(save_file)

#Creating layout for csv file
headers = ["Date","Category","Amount","Description"]
rows = []

#Inputs
print("Enter Data. Type 'Done' for Date when you are finished")
print("Enter date format as xx-xx-xxxx")
while True:
    date = input("Enter Date of spend / Or type 'done':")
    if date.lower() == "done":
        break
    category = input("Enter Type of spend (Food, Transport etc):")
    amount = float(input("Enter Money spent:"))
    description = input("What did you purchase?:")

    rows.append([date, category, amount, description])
# Writing the data to a CSV file
with open(save_file, mode='a', newline='') as file:
    writer = csv.writer(file)
    if not save_file_exists:
        writer.writerow(headers)
    writer.writerows(rows)

if save_file_exists:
    print(f'{save_file} has been updated')
else:
    print(f'{save_file} has been created using the data given')