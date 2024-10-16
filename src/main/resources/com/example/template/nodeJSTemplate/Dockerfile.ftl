# Use the official Node.js image as a base image
FROM node:16-alpine

# Set the working directory inside the container
WORKDIR /usr/src/app

# Copy the package.json and package-lock.json (if available)
COPY package*.json ./

# Install the dependencies
RUN npm install --production

# Copy the rest of the application code to the container
COPY . .

# Expose port 3000 (or whichever port your app uses)
EXPOSE 3000

# Run the Node.js application (with index.js as the entry point)
CMD ["node", "index.js"]
