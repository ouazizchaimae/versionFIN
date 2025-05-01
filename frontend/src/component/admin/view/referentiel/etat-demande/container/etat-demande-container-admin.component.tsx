import React from 'react';
import {KeyboardAvoidingView, Platform, SafeAreaView} from 'react-native';
import {globalStyle} from '../../../../../../shared/globalStyle';

import EtatDemandeAdminTabNavigation from '../../../../../../navigation/admin/referentiel/EtatDemandeAdminTabNavigation';

const EtatDemandeAdmin = () => {
  return (
    <SafeAreaView style={globalStyle.myContainer}>

      <KeyboardAvoidingView
        behavior={Platform.OS === 'ios' ? 'padding' : 'height'}
        style={{ flex: 1 }} >
        <EtatDemandeAdminTabNavigation />
      </KeyboardAvoidingView>
    </SafeAreaView>
  );
};

export default EtatDemandeAdmin;
