import React from 'react';
import {KeyboardAvoidingView, Platform, SafeAreaView} from 'react-native';
import {globalStyle} from '../../../../../../shared/globalStyle';

import EntiteAdminTabNavigation from '../../../../../../navigation/admin/referentiel/EntiteAdminTabNavigation';

const EntiteAdmin = () => {
  return (
    <SafeAreaView style={globalStyle.myContainer}>

      <KeyboardAvoidingView
        behavior={Platform.OS === 'ios' ? 'padding' : 'height'}
        style={{ flex: 1 }} >
        <EntiteAdminTabNavigation />
      </KeyboardAvoidingView>
    </SafeAreaView>
  );
};

export default EntiteAdmin;
