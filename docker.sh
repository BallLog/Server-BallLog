
function up() {
    echo `docker compose -f docker-compose.yml up -d`
}

function down() {
    echo `docker compose -f docker-compose.yml stop`
}

if [ $1 == "up" ]; then
    up
fi

if [ $1 == "down" ]; then
    down
fi