import Vue from 'vue';
import Vuetify from 'vuetify/lib/framework';

Vue.use(Vuetify);

export default new Vuetify({
    icons: {
        values: {
            warning: 'mdi-alert',
            error: 'mdi-alert-octagon',
        }
    }
});
