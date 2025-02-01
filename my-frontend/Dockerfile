# Build stage
FROM node:alpine as build

WORKDIR /app

# Copy package.json files and install dependencies
COPY package.json /app/package.json
RUN npm install

# Copy source code and build
COPY . /app
RUN npm run build

# Nginx stage
FROM nginx:stable-alpine

# Copy built files from build stage (using correct 'dist' directory)
COPY --from=build /app/dist /usr/share/nginx/html
# Copy nginx.conf from the local directory
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]