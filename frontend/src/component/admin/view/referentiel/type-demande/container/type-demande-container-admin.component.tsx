import React from 'react';
import {KeyboardAvoidingView, Platform, SafeAreaView} from 'react-native';
import {globalStyle} from '../../../../../../shared/globalStyle';

import TypeDemandeAdminTabNavigation from '../../../../../../navigation/admin/referentiel/TypeDemandeAdminTabNavigation';

const TypeDemandeAdmin = () => {
  return (
    <SafeAreaView style={globalStyle.myContainer}>

      <KeyboardAvoidingView
        behavior={Platform.OS === 'ios' ? 'padding' : 'height'}
        style={{ flex: 1 }} >
        <TypeDemandeAdminTabNavigation />
      </KeyboardAvoidingView>
    </SafeAreaView>
  );
};

export default TypeDemandeAdmin;
