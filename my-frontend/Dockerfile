# Этап сборки фронтенда
FROM node:alpine as build

WORKDIR /app

# Копирование файлов package.json и package-lock.json для установки зависимостей
COPY package.json /app/package.json
RUN npm install

# Копирование остального кода и сборка
COPY . /app
RUN npm run build

# Этап с Nginx
FROM nginx:stable-alpine

# Копирование собранных файлов из первого этапа
COPY --from=build /app/build /usr/share/nginx/html
COPY --from=build /app/nginx.conf /etc/nginx/conf.d/default.conf

# Использование стандартного порта для Nginx
EXPOSE 80

# Запуск Nginx
CMD ["nginx", "-g", "daemon off;"]