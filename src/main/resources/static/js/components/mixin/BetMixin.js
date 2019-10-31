export default {
    methods: {
        betResultText: function (resultCode) {
            switch (resultCode) {
                case -1:
                    return 'lose'
                case 0:
                    return  'не рассчитано'
                case 1:
                    return  'win'
            }
        }
    }
}