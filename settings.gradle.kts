rootProject.name="vanily"

include("infra")
include("minecraft:common:core")
include("minecraft:common:essentials")
include("minecraft:common:scoreboard")
include("minecraft:common:token")
include("minecraft:lobby:lobby")
include("minecraft:rankup:currencies")
include("minecraft:proxy:core")
include("minecraft:authentication:login")
include("web-api:currencies-service")
include("web-api:users-service")
include("minecraft:common:token")
findProject(":minecraft:common:token")?.name = "token"
