
export default {
    methods: {
        gameStatusText: function (statusCode) {
            switch (statusCode) {
                case 3:
                    return 'завершен'
                case 0:
                    return 'не начат'
                default:
                    return 'неизвестно'
            }
        },
        gameScoresText: function (game) {
            if (game.status != 3)
                return ''
            else
                return '(' + game.scoresHome + ' - ' + game.scoresAway +')'
        },
        eventBetText : function (eventCode) {
            switch (eventCode) {
                case 1:
                    return 'П1'
                case 2:
                    return 'Х'
                case 3:
                    return 'П2'
                default:
                    return  '-'
            }
        }
    }
}