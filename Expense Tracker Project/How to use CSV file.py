import csv

# Prompt user for the file name
file_name = input("Enter the name of the CSV file to create (e.g., data.csv): ")

# Collect column headers
headers = ["Name", "Age", "City"]  # Fixed headers
rows = []

# Collect data from the user
print("Enter rows of data. Type 'done' for Name when you are finished.")
while True:
    name = input("Enter Name (or type 'done' to finish): ")
    if name.lower() == 'done':
        break
    age = input("Enter Age: ")
    city = input("Enter City: ")

    # Append the row to the list
    rows.append([name, age, city])

# Write data to a CSV file
with open(file_name, mode='w', newline='') as file:
    writer = csv.writer(file)
    writer.writerow(headers)  # Write the headers
    writer.writerows(rows)    # Write the data rows

print(f"CSV file '{file_name}' has been created with the provided data.")
